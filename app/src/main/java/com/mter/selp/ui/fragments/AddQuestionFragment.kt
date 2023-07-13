package com.mter.selp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.mter.selp.databinding.FragmentAddQuestionBinding


class AddQuestionFragment: BaseFragment() {
    private lateinit var binding: FragmentAddQuestionBinding
    private var question = arrayOf<String>()
    private var result = arrayOf<Array<String>>()
    private val dataModel : DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAction()

    }

    private fun initAction(){

        binding.cancelAddTest.setOnClickListener {
            openFragment(AddTestsFragment())
        }

        binding.saveTest.setOnClickListener {
            dataModel.questionsAfterAdd.observe(activity as LifecycleOwner) {
                result = it
            }

            question += binding.answer.text.toString()
            question += binding.answer1.text.toString()
            question += binding.answer2.text.toString()
            question += binding.answer3.text.toString()
            question += binding.answer4.text.toString()

            result += question
            dataModel.questionsAfterAdd.value = result

            openFragment(AddTestsFragment())
        }

    }

}

