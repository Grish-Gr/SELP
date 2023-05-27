package com.mter.selp.ui.fragments

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.mter.selp.R

class VideoFragment : AppCompatActivity() {

    var videoView: VideoView? = null
    private var play_def_video: Button? = null
    private var pause_def_video: Button? = null
    private var reset_def_video: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.video_fragment)

        videoView = findViewById(R.id.definition_video) as VideoView?
        play_def_video = findViewById(R.id.play_def_video)
        pause_def_video = findViewById(R.id.pause_def_video)
        reset_def_video = findViewById(R.id.restart_def_video)

        videoView!!.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.example_video))
        videoView!!.requestFocus()
        videoView!!.start()
        videoView!!.setOnCompletionListener {
            Toast.makeText(applicationContext, "Video End", Toast.LENGTH_LONG).show()
        }

    }
    fun playVideo(view: View){
        videoView!!.start()
    }

    fun pauseVideo(view: View){
        videoView!!.pause()
    }

    fun resetVideo(view: View){
        videoView!!.stopPlayback()
        videoView!!.resume()
    }

}
