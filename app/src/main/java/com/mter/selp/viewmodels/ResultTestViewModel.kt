package com.mter.selp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mter.selp.model.ResultTest
import com.mter.selp.model.ResultTestRepository
import com.mter.selp.model.SleepRepository
import kotlinx.coroutines.launch

class ResultTestViewModel: ViewModel() {
    private val _listResultTest = MutableLiveData<List<ResultTest>>()
    val listResultTest: LiveData<List<ResultTest>> = _listResultTest

    fun getListResultTest(){
        viewModelScope.launch {
            val res = ResultTestRepository.getResultTests()
            Log.e("Get list", res.toString())
            _listResultTest.postValue(res)
        }
    }

    fun saveResultTest(type:Int, result: String){
        viewModelScope.launch {
            ResultTestRepository.saveResultTests(ResultTest(0L, type, result))
        }
    }


}