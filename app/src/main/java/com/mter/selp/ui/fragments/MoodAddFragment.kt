package com.mter.selp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mter.selp.R
import com.mter.selp.databinding.FragmentAddMoodBinding
import com.mter.selp.viewmodels.MoodAnalyzedViewModel

class MoodAddFragment: BaseFragment() {

    private lateinit var binding: FragmentAddMoodBinding
    private val viewModel by viewModels<MoodAnalyzedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddMoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAction()
    }

    private fun initAction(){
        binding.cancel.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.saveMood.setOnClickListener {
            val moodState = when(binding.moodChoice.checkedRadioButtonId){
                R.id.mood_5 -> 5
                R.id.mood_4 -> 4
                R.id.mood_3 -> 3
                R.id.mood_2 -> 2
                else -> 1
            }
            val procMood = when(binding.moodChoice2.checkedRadioButtonId){
                R.id.proc_15 -> 0.15
                R.id.proc_30 -> 0.30
                R.id.proc_45 -> 0.45
                R.id.proc_60 -> 0.60
                R.id.proc_75 -> 0.75
                R.id.proc_90 -> 0.90
                else -> 100.0
            }
            val state = when(binding.moodChoice3.checkedRadioButtonId){
                R.id.state_1 -> 1
                R.id.state_2 -> 2
                R.id.state_3 -> 3
                R.id.state_4 -> 4
                else -> 5
            }
            viewModel.saveMoodStat(((moodState * procMood) + state).toFloat())
            parentFragmentManager.popBackStack()
        }
    }
}