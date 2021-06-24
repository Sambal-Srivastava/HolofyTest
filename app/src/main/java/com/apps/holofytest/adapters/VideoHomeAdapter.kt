package com.apps.holofytest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apps.holofytest.R
import com.apps.holofytest.models.VideosModel
import com.bumptech.glide.RequestManager

class VideoHomeAdapter(val mediaObjects: MutableList<VideosModel.Category>, val requestManager: RequestManager?) :
    RecyclerView.Adapter<VideoHomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_items_videos, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (mediaObjects.size == 0) {
            return 0
        } else {
            return mediaObjects.get(0).videos.size
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(mediaObjects.get(0), position, requestManager)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvVideoTitle = itemView.findViewById<TextView>(R.id.tvVideoTitle)
        val ivThumbnail = itemView.findViewById<ImageView>(R.id.ivThumbnail)
        val flMediaContainer = itemView.findViewById<FrameLayout>(R.id.flMediaContainer)
        val progressBar = itemView.findViewById<ProgressBar>(R.id.progressBar)
        val parent =itemView
        lateinit var requestManager: RequestManager

        fun onBind(videoDataModel: VideosModel.Category, position:Int, requestManager: RequestManager?) {
            this.requestManager = requestManager!!
            parent.setTag(this)
            tvVideoTitle.setText(videoDataModel.videos.get(position).title)
            requestManager
                .load(videoDataModel.videos.get(position).thumb)
                .into(ivThumbnail)
        }
    }
}

