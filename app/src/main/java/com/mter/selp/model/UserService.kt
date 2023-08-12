package com.mter.selp.model

import com.mter.selp.data.network.AuthRestService
import com.mter.selp.data.network.UserRestService
import com.mter.selp.model.data.UserInfo

object UserService {

    private lateinit var userRestService: UserRestService

    fun init(
        userRestService: UserRestService,
    ) {
        this.userRestService = userRestService
    }

    suspend fun getUserInfo(): ResultOf<UserInfo> {
        return try {
            val response = userRestService.getInfoUser()
            ResultOf.Success(UserInfo.from(response))
        } catch (ex: Exception) {
            ResultOf.Error(ex)
        }
    }

    suspend fun getAdminPermission(secretKey: String): ResultOf<UserInfo> {
        return try {
            val response = userRestService.getAdminPermission(secretKey)
            ResultOf.Success(UserInfo.from(response))
        } catch (ex: Exception) {
            ResultOf.Error(ex)
        }
    }
}