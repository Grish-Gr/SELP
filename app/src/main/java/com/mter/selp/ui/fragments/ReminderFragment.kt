package com.mter.selp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.mter.selp.R
import com.mter.selp.databinding.FragmentReminderBinding

class ReminderFragment: DialogFragment() {

    private lateinit var binding: FragmentReminderBinding
    private var openMoodHint = true
    private val moodHint = MoodHintFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReminderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAction()
    }

    private fun initAction(){
        binding.cancel.setOnClickListener {
            this.dismiss()
        }
        binding.goToMood.setOnClickListener {
            goToMood(MoodFragment())
            if (openMoodHint) {
                moodHint.show(parentFragmentManager, null)
                openMoodHint = false
            }
            this.dismiss()
        }
    }

    private fun goToMood (fragment: Fragment){
        parentFragmentManager.beginTransaction()
            .replace(R.id.container_fragment, fragment)
            .addToBackStack(null)
            .commit()
    }
}