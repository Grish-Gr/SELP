package com.mter.selp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.mter.selp.R
import com.mter.selp.databinding.FragmentMoodBinding
import com.mter.selp.ui.fragments.charts.ViewChartsAdapter
import com.mter.selp.viewmodels.MoodAnalyzedViewModel

class MoodFragment: BaseFragment() {
    private lateinit var binding: FragmentMoodBinding
    private val viewModel by viewModels<MoodAnalyzedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPagerMoodCharts()
        initAction()

        showHintDialog(
            message = resources.getString(R.string.message_hint_mood_dialog),
            actionCancel = {
                enabledHintFragment(MOOD_HINT_DIALOG_ENABLED, false)
            },
            codeDialog = MOOD_HINT_DIALOG_ENABLED
        )
    }

    private fun initAction() {
        binding.account.setOnClickListener {
            openFragment(AccountFragment())
        }
        binding.addMood.setOnClickListener {
            openFragment(MoodAddFragment())
        }
    }

    private fun initPagerMoodCharts() {
        binding.viewPagerMoodCharts.adapter = ViewChartsAdapter(this)
        TabLayoutMediator(binding.tabsMoodCharts, binding.viewPagerMoodCharts) { tab, position ->
            if (position == 0){
                tab.setText(R.string.title_tab_on_day)
            } else {
                tab.setText(R.string.title_tab_on_week)
            }
        }.attach()
    }

}
