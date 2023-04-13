package com.mter.selp.ui.fragments

import android.media.MediaParser
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import com.mter.selp.R
import com.mter.selp.databinding.FragmentVolumeHelpBreathBinding

class BreathHelpVolumeFragment: BaseFragment() {

    private lateinit var binding: FragmentVolumeHelpBreathBinding
    private lateinit var increaseAnim: Animation
    private lateinit var decreaseAnim: Animation
    private lateinit var increaseMedia: MediaPlayer
    private lateinit var decreaseMedia: MediaPlayer
    private var isPlayBreathHelp = true
    private var isVolumeBreathHelp = true


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVolumeHelpBreathBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMediaRes()
        initAnimation()
        initAction()
        binding.indicatorPulseHelpBreath.startAnimation(increaseAnim)
    }

    private fun initAction(){
        binding.playBreathHelp.setOnClickListener {
            isPlayBreathHelp = if (isPlayBreathHelp){
                it.setBackgroundResource(R.drawable.ic_play)
                binding.indicatorPulseHelpBreath.clearAnimation()
                false
            } else {
                it.setBackgroundResource(R.drawable.ic_pause)
                binding.indicatorPulseHelpBreath.startAnimation(increaseAnim)
                true
            }
        }
        binding.volumeBreathHelp.setOnClickListener {
            isVolumeBreathHelp = if (isVolumeBreathHelp){
                it.setBackgroundResource(R.drawable.ic_volume_off)
                decreaseMedia.stop()
                increaseMedia.stop()
                false
            } else {
                it.setBackgroundResource(R.drawable.ic_volume_on)
                true
            }
        }
    }

    private fun initAnimation(){
        increaseAnim = AnimationUtils.loadAnimation(this.context, R.anim.anim_increase_indicator)
        increaseAnim.setAnimationListener(object : AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
                decreaseMedia.stop()
                increaseMedia.start()
            }
            override fun onAnimationEnd(p0: Animation?) {
                if (!isPlayBreathHelp) return
                binding.indicatorPulseHelpBreath.startAnimation(decreaseAnim)
            }
            override fun onAnimationRepeat(p0: Animation?) {}
        })

        decreaseAnim = AnimationUtils.loadAnimation(this.context, R.anim.anim_decrease_indicator)
        decreaseAnim.setAnimationListener(object : AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
                increaseMedia.stop()
                decreaseMedia.start()
            }
            override fun onAnimationEnd(p0: Animation?) {
                if (!isPlayBreathHelp) return
                binding.indicatorPulseHelpBreath.startAnimation(increaseAnim)
            }
            override fun onAnimationRepeat(p0: Animation?) {}
        })
    }

    private fun initMediaRes(){
        increaseMedia = MediaPlayer.create(this@BreathHelpVolumeFragment.context, R.raw.ex)
        decreaseMedia = MediaPlayer.create(this@BreathHelpVolumeFragment.context, R.raw.ex)
    }
}