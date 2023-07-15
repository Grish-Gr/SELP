package com.mter.selp.ui.fragments

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

    val questionsAfterAdd: MutableLiveData<Array<Array<String>>> by lazy {
        MutableLiveData<Array<Array<String>>>()
    }

}