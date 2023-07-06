package com.mter.selp.ui.fragments.charts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.mter.selp.R
import com.mter.selp.databinding.FragmentDayMoodBinding
import com.mter.selp.databinding.FragmentDayMoodForProgressBinding
import com.mter.selp.databinding.FragmentWeekMoodBinding
import com.mter.selp.model.Mood
import com.mter.selp.viewmodels.MoodAnalyzedViewModel
import java.text.SimpleDateFormat
import java.util.Date

class MoodDayForProgressFragment: Fragment() {
    private lateinit var binding: FragmentDayMoodForProgressBinding
    private val viewModel by viewModels<MoodAnalyzedViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDayMoodForProgressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.listMoodStat.observe(this.viewLifecycleOwner){
            showChart(it)
        }
        viewModel.getMoodsOnDay()
    }

    private fun showChart(listMoodStat: List<Mood>){
        val entries = ArrayList<Entry>()
        listMoodStat.mapIndexed { index, mood ->
            entries.add(Entry(index.toFloat(), mood.moodState, SimpleDateFormat("hh:mm").format(
                Date(mood.date)
            )))
        }
        val dataset = LineDataSet(entries, resources.getString(R.string.title_tab_on_day))
        dataset.mode = LineDataSet.Mode.CUBIC_BEZIER
        dataset.setDrawFilled(true)

        binding.chartDayMood.data = LineData(dataset)

        binding.chartDayMood.xAxis.setDrawGridLines(false)
        binding.chartDayMood.axisLeft.setDrawGridLines(false)

        with(binding.chartDayMood.xAxis) {
            position = XAxis.XAxisPosition.BOTTOM
            valueFormatter = IndexAxisValueFormatter(entries.map {
                it.data.toString()
            })
        }

        binding.chartDayMood.data = LineData(dataset)
        binding.chartDayMood.invalidate()
    }
}