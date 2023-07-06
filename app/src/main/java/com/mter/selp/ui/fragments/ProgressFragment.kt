package com.mter.selp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.mter.selp.R
import com.mter.selp.databinding.FragmentProgressBinding
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.mter.selp.model.Sleep
import com.mter.selp.ui.fragments.charts.ViewChartsAdapterForProgress
import com.mter.selp.viewmodels.SleepAnalyzedViewModel
import java.text.SimpleDateFormat
import java.util.Date

class ProgressFragment: BaseFragment() {

    private lateinit var binding: FragmentProgressBinding
    private val sleepViewModel by viewModels<SleepAnalyzedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProgressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPagerMoodCharts.adapter = ViewChartsAdapterForProgress(this)
        TabLayoutMediator(binding.tabsMoodCharts, binding.viewPagerMoodCharts) { tab, position ->
            if (position == 0){

                tab.setText(R.string.title_tab_on_day)
            } else {
                tab.setText(R.string.title_tab_on_week)
            }
        }.attach()
        initObserve()
    }

    private fun initObserve(){
        sleepViewModel.listSleepAnalyzed.observe(this.viewLifecycleOwner) {
            showChartAnalyzedSleep(it.reversed())
        }
        sleepViewModel.getListAnalyzedSleep()
    }


    private fun showChartAnalyzedSleep(listAnalyzedSleep: List<Sleep>){
        val entries = ArrayList<BarEntry>()
        listAnalyzedSleep.mapIndexed { index, sleep ->
            if (!entries.any { it.data == SimpleDateFormat("dd-MM").format(Date(sleep.createDate))}){
                entries.add(BarEntry(index.toFloat(), sleep.timeSleepInHour, SimpleDateFormat("dd-MM").format(Date(sleep.createDate))))
            }
        }

        val dataset = BarDataSet(entries, "Analyze sleep time")


        binding.chartSleepAnalyze.data = BarData(dataset)

        binding.chartSleepAnalyze.xAxis.setDrawGridLines(false)
        binding.chartSleepAnalyze.axisLeft.setDrawGridLines(false)

        with(binding.chartSleepAnalyze.xAxis) {
            position = XAxis.XAxisPosition.BOTTOM
            valueFormatter = IndexAxisValueFormatter(entries.map {
                it.data.toString()
            })
        }

        binding.chartSleepAnalyze.invalidate()
    }

}
