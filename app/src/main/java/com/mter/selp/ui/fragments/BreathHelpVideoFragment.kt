package com.mter.selp.ui.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import com.mter.selp.R
import com.mter.selp.databinding.FragmentVideoHelpBreathBinding

class BreathHelpVideoFragment: BaseFragment() {

    private lateinit var binding: FragmentVideoHelpBreathBinding
    private var isVideoPlaying = false

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
        binding.playHelpVideo.setOnClickListener {
            if (isVideoPlaying){
                it.foreground = AppCompatResources.getDrawable(it.context, R.drawable.ic_play)
                binding.helpBreathVideo.pause()
                isVideoPlaying = false
            }
            else {
                it.foreground = AppCompatResources.getDrawable(it.context, R.drawable.ic_pause)
                binding.helpBreathVideo.start()
                isVideoPlaying = true
            }
        }

        binding.back.setOnClickListener {
            openFragment(MainFragment())
        }
        binding.restartHelpVideo.setOnClickListener {
            binding.playHelpVideo.foreground = AppCompatResources.getDrawable(it.context, R.drawable.ic_play)
            isVideoPlaying = false
            binding.helpBreathVideo.stopPlayback()
            binding.helpBreathVideo.resume()
        }
    }

    private fun initHelpVideo(){
        val helpVideo = Uri.parse("android.resource://" + activity?.packageName + "/" + R.raw.breath_sound)
        binding.helpBreathVideo.setVideoURI(helpVideo)
    }
}
