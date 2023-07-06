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
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.mter.selp.R
import com.mter.selp.databinding.FragmentWeekMoodBinding
import com.mter.selp.databinding.FragmentWeekMoodForProgressBinding
import com.mter.selp.model.Mood
import com.mter.selp.viewmodels.MoodAnalyzedViewModel
import java.text.SimpleDateFormat
import java.util.Date

class MoodWeekForProgressFragment: Fragment() {
    private lateinit var binding: FragmentWeekMoodForProgressBinding
    private val viewModel by viewModels<MoodAnalyzedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeekMoodForProgressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.listMoodStat.observe(this.viewLifecycleOwner){
            showChart(it)
        }
        viewModel.getMoodsOnWeek()
    }

    private fun showChart(listMoodOnWeek: List<Mood>){
        val entries = ArrayList<Entry>()

        listMoodOnWeek.mapIndexed { index, mood ->
            if (!entries.any { it.data == SimpleDateFormat("dd-MM").format(Date(mood.date))}){
                entries.add(Entry(index.toFloat(), mood.moodState, SimpleDateFormat("dd-MM").format(Date(mood.date))))
            }
        }
        val dataset = LineDataSet(entries, resources.getString(R.string.title_tab_on_week))
        dataset.mode = LineDataSet.Mode.CUBIC_BEZIER
        dataset.setDrawFilled(true)

        binding.chartWeekMood.data = LineData(dataset)

        binding.chartWeekMood.xAxis.setDrawGridLines(false)
        binding.chartWeekMood.axisLeft.setDrawGridLines(false)

        with(binding.chartWeekMood.xAxis) {
            position = XAxis.XAxisPosition.BOTTOM
            valueFormatter = IndexAxisValueFormatter(entries.map {
                it.data.toString()
            })
        }

        binding.chartWeekMood.invalidate()
    }
}