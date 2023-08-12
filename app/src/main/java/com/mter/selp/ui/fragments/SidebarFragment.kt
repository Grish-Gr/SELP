package com.mter.selp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mter.selp.databinding.FragmentSidebarBinding

class SidebarFragment: BaseFragment() {
    private lateinit var binding: FragmentSidebarBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSidebarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAction()
    }

    private fun initAction() {

        binding.back.setOnClickListener {
            openFragment(MainFragment())
        }
        binding.buttAudio.setOnClickListener {
            openFragment(BreathHelpVolumeFragment())
        }
        binding.buttVideo.setOnClickListener {
            openFragment(BreathHelpVideoFragment())
        }
        binding.buttMood.setOnClickListener {
            openFragment(MoodFragment())
        }
        binding.buttSleep.setOnClickListener {
            openFragment(SleepAnalyzedFragment())
        }
        binding.buttArticles.setOnClickListener {
            openFragment(ArticlesFragment())
        }
        binding.buttMedit.setOnClickListener {
            openFragment(MeditationFragment())
        }
        binding.buttTest.setOnClickListener {
            openFragment(TestFragment())
        }
        binding.buttPremium.setOnClickListener {
            openFragment(PremiumFragment())
        }
    }
}