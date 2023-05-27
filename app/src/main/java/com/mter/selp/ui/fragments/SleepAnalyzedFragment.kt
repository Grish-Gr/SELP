package com.mter.selp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import com.mter.selp.databinding.FragmentAnalyzeSleepBinding


class SleepAnalyzedFragment: BaseFragment() {

    private lateinit var binding: FragmentAnalyzeSleepBinding

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
        showChartAnalyzedSleep()
        iniAction()
    }

    private fun iniAction(){
        binding.rangeDateAnalyzedSleep.setOnClickListener {
            openDateRange()
        }
        binding.addTimeSleep.setOnClickListener {
            openFragment(SleepTimeAddFragment())
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

    private fun showChartAnalyzedSleep(){
        val entries = ArrayList<Entry>()
        entries.add(Entry(1f, 8.2f, "22.05"))
        entries.add(Entry(2f, 7.4f, "23.05"))
        entries.add(Entry(3f, 6.9f, "24.05"))
        entries.add(Entry(4f, 8.0f, "25.05"))
        entries.add(Entry(5f, 9.2f, "26.05"))
        entries.add(Entry(6f, 6.2f, "27.05"))
        entries.add(Entry(7f, 8.2f, "28.05"))

        val dataset = LineDataSet(entries, "Analyze sleep time")
        dataset.mode = LineDataSet.Mode.CUBIC_BEZIER
        dataset.setDrawFilled(true)

        binding.chartSleepAnalyze.data = LineData(dataset)

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