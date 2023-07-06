package com.mter.selp.ui.fragments.charts

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewChartsAdapterForProgress(fragment: Fragment): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        Log.i("CR", position.toString())
        return if (position == 0){
            MoodDayForProgressFragment()

        } else{
            MoodWeekForProgressFragment()
        }

    }
}