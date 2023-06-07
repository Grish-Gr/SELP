package com.mter.selp.ui.fragments

import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mter.selp.R
import com.mter.selp.databinding.FragmentVideoInfoBinding
import java.io.IOException


class VideoInfoFragment: BaseFragment() {

    private lateinit var binding: FragmentVideoInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideoInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAction()
        initHelpVideo()
        subtitles()
    }

    private fun initAction(){
        binding.pauseInfoVideo.setOnClickListener {
            binding.infoVideo.pause()
        }
        binding.playInfoVideo.setOnClickListener {
            binding.infoVideo.start()
        }
        binding.restartInfoVideo.setOnClickListener {
            binding.infoVideo.stopPlayback()
            binding.infoVideo.resume()
        }
        binding.back.setOnClickListener {
            openFragment(InformationFragment())
        }
    }

    private fun initHelpVideo(){
        val infoVideo = Uri.parse("android.resource://" + activity?.packageName + "/" + R.raw.definition)
        binding.infoVideo.setVideoURI(infoVideo);
        binding.infoVideo.start()

    }

    private fun subtitles(){
        binding.infoVideo.setOnPreparedListener(OnPreparedListener { temp ->
            try {
                temp.addTimedTextSource(
                    "android.resource://" + activity?.packageName + "/" + R.raw.definition,
                    MediaPlayer.MEDIA_MIMETYPE_TEXT_SUBRIP
                )
            } catch (e: IOException) {
                e.printStackTrace()
            }
            temp.selectTrack(2)
            temp.setOnTimedTextListener { _, text -> binding.txtDisplay.setText(text.text) }
            temp.start()
        })
    }
}
