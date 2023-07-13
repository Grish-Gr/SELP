package com.mter.selp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.tabs.TabLayoutMediator
import com.mter.selp.R
import com.mter.selp.databinding.FragmentMoodBinding
import com.mter.selp.databinding.FragmentTestBinding
import com.mter.selp.ui.fragments.charts.ViewChartsAdapter
import com.mter.selp.viewmodels.MoodAnalyzedViewModel

class TestFragment: BaseFragment() {
    private lateinit var binding: FragmentTestBinding
    private val dataModel : DataModel by activityViewModels()

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

        dataModel.qualitySleep.observe(activity as LifecycleOwner) {
            binding.resultQualitySleep.text = it
        }

        dataModel.anxietyLevel.observe(activity as LifecycleOwner) {
            binding.resultAnxietyLevel.text = it
        }

        initAction()

    }

    private fun initAction() {
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
