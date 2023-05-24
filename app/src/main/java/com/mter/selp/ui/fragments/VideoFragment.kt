package com.mter.selp

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class VideoFragment : AppCompatActivity() {

    var videoView: VideoView? = null
    var mediaController: MediaController? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_video_help_breath)

        videoView = findViewById(R.id.help_breath_video) as VideoView?

        if (mediaController == null){
            mediaController = MediaController(this  )
            mediaController!!.setAnchorView(videoView)
        }
        videoView!!.setMediaController(mediaController)
        videoView!!.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.example_video))
        videoView!!.requestFocus()
        videoView!!.start()
        videoView!!.setOnCompletionListener {
            Toast.makeText(applicationContext, "Video End", Toast.LENGTH_LONG).show()
        }

        videoView!!.setOnErrorListener { mediaPlayer, i, i2 ->
            Toast.makeText(applicationContext, "Error Occered", Toast.LENGTH_LONG).show()
            false
        }
    }
}
