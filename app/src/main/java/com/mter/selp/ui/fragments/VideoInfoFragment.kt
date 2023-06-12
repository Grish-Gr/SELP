package com.mter.selp.ui.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import com.mter.selp.R
import com.mter.selp.databinding.FragmentVideoInfoBinding


class VideoInfoFragment: BaseFragment() {

    private lateinit var binding: FragmentVideoInfoBinding
    private var isVideoPlaying = false

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
    }

    private fun initAction(){
        binding.playInfoVideo.setOnClickListener {
            if (isVideoPlaying) {
                it.foreground = AppCompatResources.getDrawable(it.context, R.drawable.ic_play)
                binding.infoVideo.pause()
                isVideoPlaying = false
            }
            else{
                it.foreground = AppCompatResources.getDrawable(it.context, R.drawable.ic_pause)
                binding.infoVideo.start()
                isVideoPlaying = true
            }
        }

        binding.restartInfoVideo.setOnClickListener {
            binding.playInfoVideo.foreground = AppCompatResources.getDrawable(it.context, R.drawable.ic_play)
            isVideoPlaying = false
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
    }

}
