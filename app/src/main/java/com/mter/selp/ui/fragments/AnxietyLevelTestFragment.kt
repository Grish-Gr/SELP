package com.mter.selp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.mter.selp.R
import com.mter.selp.databinding.FragmentAnxietyLevelTestBinding
import com.mter.selp.viewmodels.DataModel
import com.mter.selp.viewmodels.ResultTestViewModel

class AnxietyLevelTestFragment: BaseFragment() {

    private lateinit var binding: FragmentAnxietyLevelTestBinding
    private val viewModel by viewModels<ResultTestViewModel>()
    private val dataModel : DataModel by activityViewModels()
    private val typeTest: Int = 2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnxietyLevelTestBinding.inflate(inflater, container, false)
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

            val anxietyQ1 = when(binding.anxietyLevelTestQuestion1Choice.checkedRadioButtonId){
                R.id.anxiety_level_test_question_1_4 -> 4
                R.id.anxiety_level_test_question_1_3 -> 3
                R.id.anxiety_level_test_question_1_2 -> 2
                else -> 1
            }

            val anxietyQ2 = when(binding.anxietyLevelTestQuestion2Choice.checkedRadioButtonId){
                R.id.anxiety_level_test_question_2_4 -> 4
                R.id.anxiety_level_test_question_2_3 -> 3
                R.id.anxiety_level_test_question_2_2 -> 2
                else -> 1
            }

            val anxietyQ3 = when(binding.anxietyLevelTestQuestion3Choice.checkedRadioButtonId){
                R.id.anxiety_level_test_question_3_4 -> 4
                R.id.anxiety_level_test_question_3_3 -> 3
                R.id.anxiety_level_test_question_3_2 -> 2
                else -> 1
            }

            val anxietyQ4 = when(binding.anxietyLevelTestQuestion4Choice.checkedRadioButtonId){
                R.id.anxiety_level_test_question_4_4 -> 4
                R.id.anxiety_level_test_question_4_3 -> 3
                R.id.anxiety_level_test_question_4_2 -> 2
                else -> 1
            }

            val anxietyQ5 = when(binding.anxietyLevelTestQuestion5Choice.checkedRadioButtonId){
                R.id.anxiety_level_test_question_5_4 -> 4
                R.id.anxiety_level_test_question_5_3 -> 3
                R.id.anxiety_level_test_question_5_2 -> 2
                else -> 1
            }

            val anxietyQ6 = when(binding.anxietyLevelTestQuestion6Choice.checkedRadioButtonId){
                R.id.anxiety_level_test_question_6_4 -> 4
                R.id.anxiety_level_test_question_6_3 -> 3
                R.id.anxiety_level_test_question_6_2 -> 2
                else -> 1
            }

            val anxietyQ7 = when(binding.anxietyLevelTestQuestion7Choice.checkedRadioButtonId){
                R.id.anxiety_level_test_question_7_4 -> 4
                R.id.anxiety_level_test_question_7_3 -> 3
                R.id.anxiety_level_test_question_7_2 -> 2
                else -> 1
            }

            val anxietyQ8 = when(binding.anxietyLevelTestQuestion8Choice.checkedRadioButtonId){
                R.id.anxiety_level_test_question_8_4 -> 4
                R.id.anxiety_level_test_question_8_3 -> 3
                R.id.anxiety_level_test_question_8_2 -> 2
                else -> 1
            }

            val anxietyQ9 = when(binding.anxietyLevelTestQuestion9Choice.checkedRadioButtonId){
                R.id.anxiety_level_test_question_9_4 -> 4
                R.id.anxiety_level_test_question_9_3 -> 3
                R.id.anxiety_level_test_question_9_2 -> 2
                else -> 1
            }

            val anxietyQ10 = when(binding.anxietyLevelTestQuestion10Choice.checkedRadioButtonId){
                R.id.anxiety_level_test_question_10_4 -> 4
                R.id.anxiety_level_test_question_10_3 -> 3
                R.id.anxiety_level_test_question_10_2 -> 2
                else -> 1
            }

            val anxietyQ11 = when(binding.anxietyLevelTestQuestion11Choice.checkedRadioButtonId){
                R.id.anxiety_level_test_question_11_4 -> 4
                R.id.anxiety_level_test_question_11_3 -> 3
                R.id.anxiety_level_test_question_11_2 -> 2
                else -> 1
            }

            val anxietyQ12 = when(binding.anxietyLevelTestQuestion12Choice.checkedRadioButtonId){
                R.id.anxiety_level_test_question_12_4 -> 4
                R.id.anxiety_level_test_question_12_3 -> 3
                R.id.anxiety_level_test_question_12_2 -> 2
                else -> 1
            }

            val anxietyQ13 = when(binding.anxietyLevelTestQuestion13Choice.checkedRadioButtonId){
                R.id.anxiety_level_test_question_13_4 -> 4
                R.id.anxiety_level_test_question_13_3 -> 3
                R.id.anxiety_level_test_question_13_2 -> 2
                else -> 1
            }

            val anxietyQ14 = when(binding.anxietyLevelTestQuestion14Choice.checkedRadioButtonId){
                R.id.anxiety_level_test_question_14_4 -> 4
                R.id.anxiety_level_test_question_14_3 -> 3
                R.id.anxiety_level_test_question_14_2 -> 2
                else -> 1
            }

            val anxietyQ15 = when(binding.anxietyLevelTestQuestion15Choice.checkedRadioButtonId){
                R.id.anxiety_level_test_question_15_4 -> 4
                R.id.anxiety_level_test_question_15_3 -> 3
                R.id.anxiety_level_test_question_15_2 -> 2
                else -> 1
            }

            val anxietyQ16 = when(binding.anxietyLevelTestQuestion16Choice.checkedRadioButtonId){
                R.id.anxiety_level_test_question_16_4 -> 4
                R.id.anxiety_level_test_question_16_3 -> 3
                R.id.anxiety_level_test_question_16_2 -> 2
                else -> 1
            }

            val anxietyQ17 = when(binding.anxietyLevelTestQuestion17Choice.checkedRadioButtonId){
                R.id.anxiety_level_test_question_17_4 -> 4
                R.id.anxiety_level_test_question_17_3 -> 3
                R.id.anxiety_level_test_question_17_2 -> 2
                else -> 1
            }

            val anxietyQ18 = when(binding.anxietyLevelTestQuestion18Choice.checkedRadioButtonId){
                R.id.anxiety_level_test_question_18_4 -> 4
                R.id.anxiety_level_test_question_18_3 -> 3
                R.id.anxiety_level_test_question_18_2 -> 2
                else -> 1
            }

            val anxietyQ19 = when(binding.anxietyLevelTestQuestion19Choice.checkedRadioButtonId){
                R.id.anxiety_level_test_question_19_4 -> 4
                R.id.anxiety_level_test_question_19_3 -> 3
                R.id.anxiety_level_test_question_19_2 -> 2
                else -> 1
            }


            val resultList = listOf(anxietyQ1, anxietyQ2, anxietyQ3, anxietyQ4, anxietyQ5, anxietyQ6, anxietyQ7, anxietyQ8, anxietyQ9,
                anxietyQ10, anxietyQ11, anxietyQ12, anxietyQ13, anxietyQ14, anxietyQ15, anxietyQ16, anxietyQ17, anxietyQ18, anxietyQ19)
            val resultAnxietyLevel = resultList.sum()

            var result: String = if (resultAnxietyLevel <= 44) {
                "Тревога отсутствует \n" +
                        "Степень тревоги в пределах обычного здорового человека"
            } else {
                if (resultAnxietyLevel <= 59) {
                    "Слабая степень тревоги"
                } else {
                    if (resultAnxietyLevel <= 74) {
                        "Средняя степень тревоги. \n" +
                                " В связи полученными Вами результатами, рекомендуем обратиться к специалисту."
                    } else {
                        "Высокая степень тревоги \n" +
                                "В связи с полученными Вами результатами, рекомендуем обратиться к специалисту."
                    }
                }
            }

            result = "$resultAnxietyLevel $result"

            dataModel.anxietyLevel.value = result
            viewModel.saveResultTest(typeTest, result)

            parentFragmentManager.popBackStack()
        }
    }
}