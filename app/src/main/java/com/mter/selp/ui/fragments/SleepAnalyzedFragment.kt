package com.mter.selp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.android.material.datepicker.MaterialDatePicker
import com.mter.selp.databinding.FragmentAnalyzeSleepBinding
import com.mter.selp.model.Sleep
import com.mter.selp.viewmodels.SleepAnalyzedViewModel
import java.text.DateFormat
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
        val entries = ArrayList<Entry>()
        listAnalyzedSleep.mapIndexed { index, sleep ->
            if (!entries.any { it.data == SimpleDateFormat("dd-MM").format(Date(sleep.createDate))}){
                entries.add(Entry(index.toFloat(), sleep.timeSleepInHour, SimpleDateFormat("dd-MM").format(Date(sleep.createDate))))
            }
        }

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