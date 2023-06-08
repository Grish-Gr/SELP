package com.mter.selp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.MaterialTimePicker.INPUT_MODE_CLOCK
import com.google.android.material.timepicker.TimeFormat
import com.mter.selp.R
import com.mter.selp.databinding.FragmentAddTimeSleepBinding
import com.mter.selp.viewmodels.SleepAnalyzedViewModel

class SleepTimeAddFragment: BaseFragment() {

    private lateinit var binding: FragmentAddTimeSleepBinding
    private val viewModel by viewModels<SleepAnalyzedViewModel>()
    private var timeBeingToBedHour = 23
    private var timeBeingToBedMin = 0
    private var timeAwakeHour = 7
    private var timeAwakeMin = 10

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTimeSleepBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAction()
    }

    private fun initAction(){
        binding.back.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.addTimeGoingToBed.setOnClickListener {
            openDialogBeingToBed()
        }
        binding.addTimeAwakening.setOnClickListener {
            openDialogAwake()
        }
        binding.saveTotalSleepTime.setOnClickListener {
            var totalHour = if (timeBeingToBedHour > timeAwakeHour){
                (24 - timeBeingToBedHour) + timeAwakeHour
            } else {
                timeAwakeHour - timeBeingToBedHour
            }
            var totalMin = 0
            if (timeBeingToBedMin > timeAwakeMin){
                totalMin = (60 - timeBeingToBedMin) + timeAwakeMin
                totalHour -= 1
            } else {
                totalMin = timeAwakeMin - timeBeingToBedMin
            }
            viewModel.saveAnalyzeSleep(totalHour + (totalMin / 60f))
            parentFragmentManager.popBackStack()
        }
    }

    private fun openDialogBeingToBed(){
        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setHour(23)
            .setMinute(0)
            .setInputMode(INPUT_MODE_CLOCK)
            .setTitleText(R.string.title_dialog_add_sleep_go_bed)
            .build()
        picker.addOnPositiveButtonClickListener {
            timeBeingToBedHour = picker.hour
            timeBeingToBedMin = picker.minute
            var totalHour = if (timeBeingToBedHour > timeAwakeHour){
                (24 - timeBeingToBedHour) + timeAwakeHour
            } else {
                timeAwakeHour - timeBeingToBedHour
            }
            var totalMin = 0
            if (timeBeingToBedMin > timeAwakeMin){
                totalMin = (60 - timeBeingToBedMin) + timeAwakeMin
                totalHour -= 1
            } else {
                totalMin = timeAwakeMin - timeBeingToBedMin
            }


            binding.timeGoingToBed.text = "${picker.hour}:${picker.minute}"
            binding.totalTime.text = "${totalHour}:${totalMin}"
        }
        picker.show(parentFragmentManager, null)
    }

    private fun openDialogAwake(){
        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setHour(7)
            .setMinute(10)
            .setInputMode(INPUT_MODE_CLOCK)
            .setTitleText(R.string.title_dialog_add_sleep_awake)
            .build()
        picker.addOnPositiveButtonClickListener {
            timeAwakeHour = picker.hour
            timeAwakeMin = picker.minute
            var totalHour = if (timeBeingToBedHour > timeAwakeHour){
                (24 - timeBeingToBedHour) + timeAwakeHour
            } else {
                timeAwakeHour - timeBeingToBedHour
            }
            var totalMin = 0
            if (timeBeingToBedMin > timeAwakeMin){
                totalMin = (60 - timeBeingToBedMin) + timeAwakeMin
                totalHour -= 1
            } else {
                totalMin = timeAwakeMin - timeBeingToBedMin
            }

            binding.timeAwakening.text = "${picker.hour}:${picker.minute}"
            binding.totalTime.text = "${totalHour}:${totalMin}"
        }
        picker.show(parentFragmentManager, null)

    }
}