package com.mter.selp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mter.selp.databinding.FragmentMoodBinding
import com.mter.selp.ui.fragments.charts.ViewChartsAdapter
import java.io.Console
import kotlin.math.log

class MoodFragment: BaseFragment() {
    private lateinit var binding: FragmentMoodBinding

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

        binding.viewPagerMoodCharts.adapter = ViewChartsAdapter(this)
        TabLayoutMediator(binding.tabsMoodCharts, binding.viewPagerMoodCharts) { tab, position ->
            if (position == 0){
                tab.text = "За день"
            } else {
                tab.text = "За неделю"
            }
        }.attach()
        initAction()
    }

    private fun initAction(){
        binding.account.setOnClickListener{
            openFragment(AccountFragment())
        }
        binding.addMood.setOnClickListener {
            val dialog = MoodAddDialogFragment()
            dialog.show(parentFragmentManager, null)
        }
    }

}
