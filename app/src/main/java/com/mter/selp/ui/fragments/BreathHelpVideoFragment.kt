package com.mter.selp.ui.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mter.selp.R
import com.mter.selp.databinding.FragmentVideoHelpBreathBinding
import com.mter.selp.databinding.FragmentVolumeHelpBreathBinding

class BreathHelpVideoFragment: BaseFragment() {

    private lateinit var binding: FragmentVideoHelpBreathBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideoHelpBreathBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAction()
        initHelpVideo()
    }

    private fun initAction(){
        binding.pauseHelpVideo.setOnClickListener {
            binding.helpBreathVideo.pause()
        }
        binding.playHelpVideo.setOnClickListener {
            binding.helpBreathVideo.start()
        }
        binding.restartHelpVideo.setOnClickListener {
            binding.helpBreathVideo.stopPlayback()
            binding.helpBreathVideo.resume()
        }
    }

    private fun initHelpVideo(){
        val helpVideo = Uri.parse("android.resource://" + activity?.packageName + "/" + R.raw.example_video)
        binding.helpBreathVideo.setVideoURI(helpVideo);
        binding.helpBreathVideo.start()
    }
}
