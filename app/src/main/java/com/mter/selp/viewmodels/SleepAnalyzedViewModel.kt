package com.mter.selp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mter.selp.model.data.Sleep
import com.mter.selp.model.SleepRepository
import kotlinx.coroutines.launch
import java.util.Date

class SleepAnalyzedViewModel: ViewModel() {

    private val _listSleepAnalyzed = MutableLiveData<List<Sleep>>()
    val listSleepAnalyzed: LiveData<List<Sleep>> = _listSleepAnalyzed

    fun getListAnalyzedSleep(){
        viewModelScope.launch {
            val res = SleepRepository.getSleeps()
            Log.e("Get list", res.toString())
            _listSleepAnalyzed.postValue(res)
        }
    }

    fun getListAnalyzedSleep(startDate: Long, endDate: Long){
        viewModelScope.launch {
            _listSleepAnalyzed.postValue(SleepRepository.getSleeps(startDate, endDate))
        }
    }

    fun saveAnalyzeSleep(timeSleepInHour: Float){
        viewModelScope.launch {
            SleepRepository.save(Sleep(0, Date().time, timeSleepInHour))
        }
    }
}