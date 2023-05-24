package com.mter.selp.ui.fragments.charts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.mter.selp.databinding.FragmentWeekMoodBinding

class MoodWeekFragment: Fragment() {
    private lateinit var binding: FragmentWeekMoodBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeekMoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val entries = ArrayList<Entry>()
        entries.add(Entry(1f, 3f, "22.05"))
        entries.add(Entry(2f, 4f, "23.05"))
        entries.add(Entry(3f, 2f, "24.05"))
        entries.add(Entry(4f, 5f, "25.05"))
        entries.add(Entry(5f, 3f, "26.05"))
        entries.add(Entry(6f, 4f, "27.05"))
        entries.add(Entry(7f, 5f, "28.05"))

        val dataset = LineDataSet(entries, "Mood on week")
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