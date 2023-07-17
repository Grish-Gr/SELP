package com.mter.selp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataModel : ViewModel() {
    val name: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val mail: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val qualitySleep: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val anxietyLevel: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val selpPremium: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val questionsAfterAdd: MutableLiveData<Array<Array<String>>> by lazy {
        MutableLiveData<Array<Array<String>>>()
    }

}