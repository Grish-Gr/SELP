package com.mter.selp.ui.fragments

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import com.mter.selp.R
import com.mter.selp.databinding.FragmentMeditation3minVideoBinding

class MeditationThreeMinFragment: BaseFragment() {
    private lateinit var binding: FragmentMeditation3minVideoBinding
    private lateinit var sound: MediaPlayer
    private var startFromPlay = false
    private var isPLayAnimation = false
    private var volumeOn = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMeditation3minVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMedia()
        initAction()
    }

    private fun initAction(){
        binding.playMeditation3min.setOnClickListener {
            if (isPLayAnimation){
                it.foreground = AppCompatResources.getDrawable(it.context, R.drawable.ic_play)
                binding.volumeHelpBreath.foreground = AppCompatResources.getDrawable(it.context, R.drawable.ic_volume_off)
                sound.stop()
                isPLayAnimation = false
                volumeOn = false
            } else {
                if (sound.isPlaying){
                    sound.stop()
                    initMedia()
                }
                it.foreground = AppCompatResources.getDrawable(it.context, R.drawable.ic_pause)
                binding.volumeHelpBreath.foreground = AppCompatResources.getDrawable(it.context, R.drawable.ic_volume_on)
                sound.start()
                startFromPlay = true
                isPLayAnimation = true
                volumeOn = true
            }
        }

        binding.volumeHelpBreath.setOnClickListener {
            volumeOn = if (volumeOn){
                it.foreground = AppCompatResources.getDrawable(it.context, R.drawable.ic_volume_off)
                if (startFromPlay){
                    sound.setVolume(0.0f, 0.0f)
                }
                else{
                    sound.stop()
                    initMedia()
                }
                false

            } else {
                it.foreground = AppCompatResources.getDrawable(it.context, R.drawable.ic_volume_on)
                if (!sound.isPlaying){
                    sound.start()
                    startFromPlay = false
                }
                else{
                    sound.setVolume(1.0f, 1.0f)
                }
                true
            }
        }

        binding.back.setOnClickListener {
            sound.stop()
            parentFragmentManager.popBackStack()
        }
    }

    private fun initMedia() {
        sound = MediaPlayer.create(this@MeditationThreeMinFragment.context, R.raw.meditation_3min)
        sound.isLooping = true
    }

    override fun onDestroy() {
        sound.stop()
        super.onDestroy()
    }
}