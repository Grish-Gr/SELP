package com.mter.selp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.mter.selp.databinding.FragmentAddTestsBinding


class AddTestsFragment: BaseFragment() {
    private lateinit var binding: FragmentAddTestsBinding
    private val adapter = QuestionAdapter()
    private var answer = emptyArray<String>()
    private var answerAfterAdd = arrayOf<Array<String>>()
    private val dataModel : DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTestsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataModel.questionsAfterAdd.observe(activity as LifecycleOwner) {
            answerAfterAdd = it
        }

        initAction()
    }

    private fun initAction(){

        binding.apply {
            cancelAddTest.setOnClickListener {
                titleTest.text = null
                openFragment(TestFragment())
            }
            saveTest.setOnClickListener {
                openFragment(TestFragment())
            }
            addQuestion.setOnClickListener {
                dataModel.questionsAfterAdd.value = answerAfterAdd
                openFragment(AddQuestionFragment())
            }

            rcViewTest.layoutManager = LinearLayoutManager(context)
            rcViewTest.adapter = adapter

            refresh.setOnClickListener {

                for (answer in answerAfterAdd) {
                    val question = Question(answer[0], answer[1], answer[2], answer[3], answer[4])
                    adapter.addQuestion(question)
                }

            }

        }

    }
}