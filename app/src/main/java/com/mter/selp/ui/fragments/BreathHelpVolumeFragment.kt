package com.mter.selp.ui.fragments

import android.media.MediaPlayer
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
    private lateinit var pauseIncrease: Animation
    private lateinit var pauseDecrease: Animation
    private lateinit var sound: MediaPlayer
    private var startFromPlay = false
    private var isPLayAnimation = false
    private var volumeOn = false

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
        initMedia()
        initAnimation()
        initAction()
    }

    private fun initAction(){
        binding.playHelpBreath.setOnClickListener {
            if (isPLayAnimation){
                it.foreground = AppCompatResources.getDrawable(it.context, R.drawable.ic_play)
                binding.volumeHelpBreath.foreground = AppCompatResources.getDrawable(it.context, R.drawable.ic_volume_off)
                sound.stop()
                binding.helpBreathIndicatorCircle.clearAnimation()
                binding.hintBreath.setText(R.string.hint_breath_start)
                initMedia()
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
                binding.helpBreathIndicatorCircle.startAnimation(animationIncrease)
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

    private fun initAnimation(){
        isPLayAnimation = false
        animationIncrease = AnimationUtils.loadAnimation(this.context, R.anim.anim_increase_indicator)
        animationDecrease = AnimationUtils.loadAnimation(this.context, R.anim.anim_decrease_indicator)
        pauseIncrease = AnimationUtils.loadAnimation(this.context, R.anim.anim_increase_pause)
        pauseDecrease = AnimationUtils.loadAnimation(this.context, R.anim.anim_decrease_pause)

        animationIncrease.setAnimationListener(object : AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
                binding.hintBreath.setText(R.string.hint_breath_inhale)
            }

            override fun onAnimationEnd(p0: Animation?) {
                if (isPLayAnimation){
                    binding.helpBreathIndicatorCircle.startAnimation(pauseIncrease)
                }
            }

            override fun onAnimationRepeat(p0: Animation?) {}
        })

        pauseIncrease.setAnimationListener(object : AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
                binding.hintBreath.setText(R.string.hint_breath_pause_inhale)
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
                    binding.helpBreathIndicatorCircle.startAnimation(pauseDecrease)
                }
            }

            override fun onAnimationRepeat(p0: Animation?) {}
        })

        pauseDecrease.setAnimationListener(object : AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
                binding.hintBreath.setText(R.string.hint_breath_pause_exhale)
            }

            override fun onAnimationEnd(p0: Animation?) {
                if (isPLayAnimation){
                    binding.helpBreathIndicatorCircle.startAnimation(animationIncrease)
                }
            }

            override fun onAnimationRepeat(p0: Animation?) {}
        })

    }

    private fun initMedia(){
        sound = MediaPlayer.create(this@BreathHelpVolumeFragment.context, R.raw.breath_sound)
        sound.isLooping = true
    }

    override fun onDestroy() {
        binding.helpBreathIndicatorCircle.clearAnimation()
        sound.stop()
        super.onDestroy()
    }
}
