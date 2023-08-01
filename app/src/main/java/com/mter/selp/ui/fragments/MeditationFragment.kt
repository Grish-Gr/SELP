package com.mter.selp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mter.selp.databinding.FragmentMeditationBinding


class MeditationFragment : BaseFragment() {
    private lateinit var binding: FragmentMeditationBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMeditationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*initAction()*/
    }

    /*private fun initAction(){

        binding.openMeditation2min.setOnClickListener {
            openFragment(MeditationTwoMinFragment())
        }
        binding.openMeditation3min.setOnClickListener {
            openFragment(MeditationThreeMinFragment())
        }

    }*/

}