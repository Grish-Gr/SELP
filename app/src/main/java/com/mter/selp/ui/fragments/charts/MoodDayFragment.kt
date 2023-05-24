package com.mter.selp.ui.fragments.charts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.mter.selp.databinding.FragmentDayMoodBinding
import com.mter.selp.databinding.FragmentWeekMoodBinding

class MoodDayFragment: Fragment() {
    private lateinit var binding: FragmentDayMoodBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDayMoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val entries = ArrayList<Entry>()
        entries.add(Entry(1f, 3f, "9:00"))
        entries.add(Entry(2f, 4f, "12:00"))
        entries.add(Entry(3f, 2f, "18:00"))
        entries.add(Entry(4f, 5f, "22:00"))

        val dataset = LineDataSet(entries, "Mood on week")
        dataset.mode = LineDataSet.Mode.CUBIC_BEZIER

        binding.chartDayMood.data = LineData(dataset)
        binding.chartDayMood.invalidate()
    }
}