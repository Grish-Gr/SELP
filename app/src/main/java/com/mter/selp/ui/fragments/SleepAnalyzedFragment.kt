package com.mter.selp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.android.material.datepicker.MaterialDatePicker
import com.mter.selp.R
import com.mter.selp.databinding.FragmentAnalyzeSleepBinding
import com.mter.selp.model.Sleep
import com.mter.selp.viewmodels.SleepAnalyzedViewModel
import java.text.SimpleDateFormat
import java.util.Date


class SleepAnalyzedFragment: BaseFragment() {

    private lateinit var binding: FragmentAnalyzeSleepBinding
    private val viewModel by viewModels<SleepAnalyzedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnalyzeSleepBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserve()
        iniAction()
        showHintDialog(
            message = resources.getString(R.string.message_hint_sleep_dialog),
            actionCancel = {
                enabledHintFragment(SLEEP_HINT_DIALOG_ENABLED, false)
            },
            codeDialog = SLEEP_HINT_DIALOG_ENABLED
        )
    }

    private fun initObserve(){
        viewModel.listSleepAnalyzed.observe(this.viewLifecycleOwner) {
            showChartAnalyzedSleep(it.reversed())
        }
        viewModel.getListAnalyzedSleep()
    }

    private fun iniAction(){
        binding.rangeDateAnalyzedSleep.setOnClickListener {
            openDateRange()
        }
        binding.addTimeSleep.setOnClickListener {
            openFragment(SleepTimeAddFragment())
            viewModel.getListAnalyzedSleep()
        }
    }

    private fun openDateRange(){
        val dateRange = MaterialDatePicker
            .Builder
            .dateRangePicker()
            .setTitleText("Select a date")
            .build()

        dateRange.show(parentFragmentManager, "DATE_RANGE_PICKER")
        dateRange.addOnPositiveButtonClickListener {
            it.first
            it.second
        }
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