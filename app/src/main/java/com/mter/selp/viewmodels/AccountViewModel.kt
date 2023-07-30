package com.mter.selp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mter.selp.data.network.UserRestService
import com.mter.selp.model.UserService
import com.mter.selp.model.data.UserInfo
import kotlinx.coroutines.launch

class AccountViewModel: BaseViewModel() {

    private val _userInfo: MutableLiveData<UserInfo> = MutableLiveData();
    val login: LiveData<UserInfo> = _userInfo

    fun getPermissionAdmin(secretKey: String) {
        viewModelScope.launch {
            manipulateResult(UserService.getAdminPermission(secretKey), successAction = {
                _userInfo.postValue(it.value)
            })
        }
    }
}