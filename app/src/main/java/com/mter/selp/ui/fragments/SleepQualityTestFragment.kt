package com.mter.selp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.mter.selp.R
import com.mter.selp.databinding.FragmentSleepQualityTestBinding
import com.mter.selp.viewmodels.MoodAnalyzedViewModel
import com.mter.selp.viewmodels.ResultTestViewModel

class SleepQualityTestFragment: BaseFragment() {

    private lateinit var binding: FragmentSleepQualityTestBinding
    private val viewModel by viewModels<ResultTestViewModel>()
    private val dataModel : DataModel by activityViewModels()
    private val typeTest: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSleepQualityTestBinding.inflate(inflater, container, false)
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

            val fallingAsleepTime = when(binding.fallingAsleepTimeChoice.checkedRadioButtonId){
                R.id.sleep_5 -> 5
                R.id.sleep_4 -> 4
                R.id.sleep_3 -> 3
                R.id.sleep_2 -> 2
                else -> 1
            }

            val sleepDuration = when(binding.sleepDurationChoice.checkedRadioButtonId){
                R.id.sleep_duration_5 -> 5
                R.id.sleep_duration_4 -> 4
                R.id.sleep_duration_3 -> 3
                R.id.sleep_duration_2 -> 2
                else -> 1
            }

            val nightAwakenings = when(binding.nightAwakeningsChoice.checkedRadioButtonId){
                R.id.night_awakenings_5 -> 5
                R.id.night_awakenings_4 -> 4
                R.id.night_awakenings_3 -> 3
                R.id.night_awakenings_2 -> 2
                else -> 1
            }

            val dreams = when(binding.dreamsChoice.checkedRadioButtonId){
                R.id.dreams_5 -> 5
                R.id.dreams_4 -> 4
                R.id.dreams_3 -> 3
                R.id.dreams_2 -> 2
                else -> 1
            }

            val qualitySleep = when(binding.qualitySleepChoice.checkedRadioButtonId){
                R.id.quality_sleep_5 -> 5
                R.id.quality_sleep_4 -> 4
                R.id.quality_sleep_3 -> 3
                R.id.quality_sleep_2 -> 2
                else -> 1
            }

            val qualityAwakenings = when(binding.qualityAwakeningsChoice.checkedRadioButtonId){
                R.id.quality_awakenings_5 -> 5
                R.id.quality_awakenings_4 -> 4
                R.id.quality_awakenings_3 -> 3
                R.id.quality_awakenings_2 -> 2
                else -> 1
            }

            val resultList = listOf(fallingAsleepTime, sleepDuration, nightAwakenings, dreams, qualitySleep, qualityAwakenings)
            val resultQualitySleep = resultList.sum()
            var result: String

            if (resultQualitySleep <= 5) {
                result = "У вас отличный здоровый сон!"
            } else {
                result = if (resultQualitySleep <= 12) {
                    "Состояние сна в пределах нормы"
                } else {
                    if (resultQualitySleep <= 18) {
                        "Состояние сна нарушено"
                    } else {
                        if (resultQualitySleep <= 25) {
                            "Сон в значительной степени нарушен"
                        } else {
                            "Бессоница"
                        }
                    }
                }
            }
            result = "$resultQualitySleep $result"

            dataModel.qualitySleep.value = result
            viewModel.saveResultTest(typeTest, result)

            parentFragmentManager.popBackStack()
        }
    }
}