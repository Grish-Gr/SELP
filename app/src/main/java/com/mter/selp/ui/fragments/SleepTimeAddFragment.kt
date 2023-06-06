package com.mter.selp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.MaterialTimePicker.INPUT_MODE_CLOCK
import com.google.android.material.timepicker.TimeFormat
import com.mter.selp.databinding.FragmentAddTimeSleepBinding

class SleepTimeAddFragment: BaseFragment() {

    private lateinit var binding: FragmentAddTimeSleepBinding

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
            parentFragmentManager.popBackStack()
        }
    }

    private fun openDialogBeingToBed(){
        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setHour(23)
            .setMinute(0)
            .setInputMode(INPUT_MODE_CLOCK)
            .setTitleText("Select being to bed time")
            .build()
        picker.show(parentFragmentManager, null)
    }

    private fun openDialogAwake(){
        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setHour(7)
            .setMinute(10)
            .setInputMode(INPUT_MODE_CLOCK)
            .setTitleText("Select awaking time")
            .build()
        picker.show(parentFragmentManager, null)

    }
}