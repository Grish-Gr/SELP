package com.mter.selp.viewmodels

import android.text.method.TextKeyListener.Capitalize
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mter.selp.model.Mood
import com.mter.selp.model.MoodRepository
import kotlinx.coroutines.launch
import java.util.Date

class MoodAnalyzedViewModel: ViewModel() {
    private val _listMoodStat = MutableLiveData<List<Mood>>()
    val listMoodStat: LiveData<List<Mood>> = _listMoodStat

    fun getMoodsOnDay(){
        viewModelScope.launch {
            val currDate = Date().time
            _listMoodStat.postValue(MoodRepository.getMoods(currDate - 86400000, currDate)) //86400000 = day
        }
    }

    fun getMoodsOnWeek(){
        viewModelScope.launch {
            val currDate = Date().time
            _listMoodStat.postValue(MoodRepository.getMoods(currDate - 7 * 86400000, currDate)) //86400000 = day
        }
    }

    fun saveMoodStat(state: Float){
        viewModelScope.launch {
            MoodRepository.saveMood(Mood(0L, state, Date().time))
        }
    }

}