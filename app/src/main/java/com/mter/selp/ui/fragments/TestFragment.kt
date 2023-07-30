package com.mter.selp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.mter.selp.databinding.FragmentTestBinding
import com.mter.selp.viewmodels.DataModel
import com.mter.selp.viewmodels.ResultTestViewModel

class TestFragment: BaseFragment() {
    private lateinit var binding: FragmentTestBinding
    private val resultTestViewModel by viewModels<ResultTestViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserve()
        initAction()

    }

    private fun initObserve(){

        resultTestViewModel.listResultTest.observe(this.viewLifecycleOwner) {
            it.forEach {
                val type = it.type
                val result = "${it.result}"

                if (type == 1){
                    binding.resultQualitySleep.text = result
                } else {
                    binding.resultAnxietyLevel.text = result
                }
            }
        }
        resultTestViewModel.getListResultTest()
    }


    private fun initAction() {
        binding.back.setOnClickListener {
            openFragment(MainFragment())
        }

        binding.account.setOnClickListener {
            openFragment(AccountFragment())
        }

        binding.testQualitySleep.setOnClickListener {
            openFragment(SleepQualityTestFragment())
        }

        binding.testAnxietyLevel.setOnClickListener {
            openFragment(AnxietyLevelTestFragment())
        }

        binding.addTest.setOnClickListener {
            openFragment(AddTestsFragment())
        }

    }
}
