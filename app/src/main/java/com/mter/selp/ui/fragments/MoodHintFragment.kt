package com.mter.selp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.mter.selp.databinding.FragmentMoodHintBinding

class MoodHintFragment: DialogFragment() {

    private lateinit var binding: FragmentMoodHintBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoodHintBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAction()
    }

    private fun initAction(){
        binding.close.setOnClickListener {
            this.dismiss()
        }
        binding.notOpen.setOnClickListener {
            this.dismiss()
        }
    }

}