package com.apps.holofytest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.apps.holofytest.adapters.VideoHomeAdapter
import com.apps.holofytest.models.VideoDataModel
import com.apps.holofytest.utils.Constant
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    val videosList: ArrayList<VideoDataModel> = ArrayList()
    private lateinit var simpleExoPlayer: SimpleExoPlayer
    private lateinit var mediaDataSourceFactory: DataSource.Factory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvVideos.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

     /*   val mediaObjects: ArrayList<VideoDataModel> = ArrayList<VideoDataModel>(Arrays.asList<VideoDataModel>(*Resources.MEDIA_OBJECTS))
        rvVideos.setMediaObjects(mediaObjects)*/

        for (i in 1..10) {
            videosList.add(
                VideoDataModel(
                    getString(R.string.video_title), Constant.STREAMING_URL,
                    "https://ibb.co/9Hj94Rs",
//                    "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png",
                    getString(R.string.video_short_desription)
                )
            )
        }
        rvVideos.setMediaObjects(videosList, this)
        rvVideos.adapter = VideoHomeAdapter(videosList as List<VideoDataModel>, initGlide())

    }
    /* private fun initializePlayer() {

         simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this)

         mediaDataSourceFactory =
             DefaultDataSourceFactory(this, Util.getUserAgent(this, "mediaPlayerSample"))

         val mediaSource = ProgressiveMediaSource.Factory(mediaDataSourceFactory).createMediaSource(
             Uri.parse(Constant.STREAMING_URL)
         )

         simpleExoPlayer.prepare(mediaSource, false, false)
         simpleExoPlayer.playWhenReady = true


             playerView?.setShutterBackgroundColor(Color.TRANSPARENT)
             playerView?.player = simpleExoPlayer
             playerView?.requestFocus()

     }*/

    private fun releasePlayer() {
       // simpleExoPlayer.release()
        rvVideos.releasePlayer()
    }

    private fun initGlide(): RequestManager? {
        val options: RequestOptions = RequestOptions()
            .placeholder(R.drawable.holofy_dwn)
            .error(R.drawable.holofy_dwn)
        return Glide.with(this)
            .setDefaultRequestOptions(options)
    }

/*    public override fun onStart() {
        super.onStart()
//        if (Util.SDK_INT > 23) initializePlayer()
    }

    public override fun onResume() {
        super.onResume()

//        if (Util.SDK_INT <= 23) initializePlayer()
    }*/

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
        rvVideos.init(this)
    }
}
