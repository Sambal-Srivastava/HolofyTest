package com.apps.holofytest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.apps.holofytest.adapters.VideoHomeAdapter
import com.apps.holofytest.models.VideosModel
import com.apps.holofytest.utils.Resources
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.google.android.exoplayer2.util.Util
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var videosList: MutableList<VideosModel.Category> = ArrayList<VideosModel.Category>()
    //=========GSON=================
    var gson = Gson()
    //===================
    private lateinit var dataModel: VideosModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvVideos.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        //=============from data model========================
        dataModel = gson.fromJson(Resources.mediaJSON, VideosModel::class.java)
        //-----------------------------
        videosList.add(dataModel.categories.get(0))
        rvVideos.setMediaObjects(videosList, this)
        rvVideos.adapter = VideoHomeAdapter(videosList, initGlide())

    }

    private fun releasePlayer() {
        rvVideos.releasePlayer()
    }

    private fun initGlide(): RequestManager? {
        val options: RequestOptions = RequestOptions()
            .placeholder(R.drawable.holofy_dwn)
            .error(R.drawable.holofy_dwn)
        return Glide.with(this)
            .setDefaultRequestOptions(options)
    }


    public override fun onPause() {
        super.onPause()

        if (Util.SDK_INT <= 23) releasePlayer()
    }

    public override fun onStop() {
        super.onStop()

        if (Util.SDK_INT > 23) releasePlayer()
    }

    override fun onRestart() {
        super.onRestart()
        rvVideos.removeVideoView(rvVideos.videoSurfaceView)
        rvVideos.resetVideoView()
    }

    override fun onStart() {
        super.onStart()
        rvVideos.init(this)
    }
}
