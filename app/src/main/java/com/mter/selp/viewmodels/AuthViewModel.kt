package com.mter.selp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mter.selp.data.network.request.Sex
import com.mter.selp.model.AuthService
import com.mter.selp.model.data.UserInfo
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class AuthViewModel: BaseViewModel() {

    private val _login: MutableLiveData<String> = MutableLiveData();
    val login: LiveData<String> = _login
    private val _registration: MutableLiveData<UserInfo> = MutableLiveData();
    val registration: LiveData<UserInfo> = _registration

    fun loginInSystem(email: String, password: String) {
        viewModelScope.launch {
            val response = AuthService.login(email, password)
            manipulateResult(
                response,
                successAction = {
                    _login.postValue(it.value)
                }
            )
        }
    }

    fun registration(username: String, email: String, password: String, sex: Sex, birthdateTime: Long) {
        viewModelScope.launch {
            val response = AuthService.registration(
                username = username,
                sex = sex,
                birthdate = birthdateTime,
                email = email,
                password = password
            )
            manipulateResult(
                response,
                successAction = {
                    _registration.postValue(it.value)
                }
            )
        }
    }
}