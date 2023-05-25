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
import androidx.appcompat.content.res.AppCompatResources
import com.mter.selp.R
import com.mter.selp.databinding.FragmentVolumeHelpBreathBinding

class BreathHelpVolumeFragment: BaseFragment() {

    private lateinit var binding: FragmentVolumeHelpBreathBinding
    private lateinit var animationIncrease: Animation
    private lateinit var animationDecrease: Animation
    private var isPLayAnimation = false
    private var volumeOn = true

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
        initAnimation()
        initAction()
    }

    private fun initAction(){
        binding.playHelpBreath.setOnClickListener {
            if (isPLayAnimation){
                it.foreground = AppCompatResources.getDrawable(it.context, R.drawable.ic_play)
                binding.helpBreathIndicatorCircle.clearAnimation()
                isPLayAnimation = false
            } else {
                it.foreground = AppCompatResources.getDrawable(it.context, R.drawable.ic_pause)
                binding.helpBreathIndicatorCircle.startAnimation(animationIncrease)
                isPLayAnimation = true
            }
        }
        binding.volumeHelpBreath.setOnClickListener {
            if (volumeOn){
                it.foreground = AppCompatResources.getDrawable(it.context, R.drawable.ic_volume_off)
                volumeOn = false
            } else {
                it.foreground = AppCompatResources.getDrawable(it.context, R.drawable.ic_volume_on)
                volumeOn = true
            }
        }
        binding.back.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun initAnimation(){
        isPLayAnimation = true
        animationIncrease = AnimationUtils.loadAnimation(this.context, R.anim.anim_increase_indicator)
        animationDecrease = AnimationUtils.loadAnimation(this.context, R.anim.anim_decrease_indicator)
        animationIncrease.setAnimationListener(object : AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
                binding.hintBreath.setText(R.string.hint_breath_inhale)
            }

            override fun onAnimationEnd(p0: Animation?) {
                if (isPLayAnimation){
                    binding.helpBreathIndicatorCircle.startAnimation(animationDecrease)
                }
            }

            override fun onAnimationRepeat(p0: Animation?) {}
        })
        animationDecrease.setAnimationListener(object : AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
                binding.hintBreath.setText(R.string.hint_breath_exhale)
            }

            override fun onAnimationEnd(p0: Animation?) {
                if (isPLayAnimation) {
                    binding.helpBreathIndicatorCircle.startAnimation(animationIncrease)
                }
            }

            override fun onAnimationRepeat(p0: Animation?) {}
        })
        binding.helpBreathIndicatorCircle.startAnimation(animationIncrease)
    }

    override fun onDestroy() {
        binding.helpBreathIndicatorCircle.clearAnimation()
        super.onDestroy()
    }
}