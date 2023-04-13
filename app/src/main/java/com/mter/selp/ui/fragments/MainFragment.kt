package com.mter.selp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.mter.selp.R
import com.mter.selp.databinding.FragmentMainBinding

class MainFragment: BaseFragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAction()
    }

    private fun initAction(){
        binding.helpCard.setOnClickListener {
            openFragment(BreathHelpVolumeFragment())
        }
        binding.exercisesProgessive.setOnClickListener {
            openFragment(MeditationFragment())
        }
    }

    private companion object{
        const val SETTING = "Settings"
        const val HELP_WITH_SOUND = "KeyHelpWithSound"
    }
}