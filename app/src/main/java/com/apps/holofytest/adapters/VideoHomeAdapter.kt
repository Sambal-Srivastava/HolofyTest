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
import com.apps.holofytest.models.VideoDataModel
import com.bumptech.glide.RequestManager

class VideoHomeAdapter(val mediaObjects: List<VideoDataModel>, val requestManager: RequestManager?) :
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
            return mediaObjects.size
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       /* holder.tvVideoTitle.setText(mediaObjects.get(position).videoTitle)
        holder.tvVideoDescription.setText(mediaObjects.get(position).videodescription)*/
        (holder as VideoHomeAdapter.ViewHolder).onBind(mediaObjects.get(position), requestManager)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvVideoTitle = itemView.findViewById<TextView>(R.id.tvVideoTitle)
        val tvVideoDescription = itemView.findViewById<TextView>(R.id.tvVideoDescription)
        val ivThumbnail = itemView.findViewById<ImageView>(R.id.ivThumbnail)
        val flMediaContainer = itemView.findViewById<FrameLayout>(R.id.flMediaContainer)
        val progressBar = itemView.findViewById<ProgressBar>(R.id.progressBar)
        val parent =itemView
        lateinit var requestManager: RequestManager

        fun onBind(videoDataModel: VideoDataModel, requestManager: RequestManager?) {
            this.requestManager = requestManager!!
            parent.setTag(this)
            tvVideoTitle.setText(videoDataModel.videoTitle)
            requestManager
                .load(videoDataModel.videoThumbnail)
                .into(ivThumbnail)
        }
    }
}
