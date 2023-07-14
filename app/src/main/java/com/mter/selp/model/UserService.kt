package com.mter.selp.model

import com.mter.selp.data.network.AuthRestService
import com.mter.selp.data.network.UserRestService
import com.mter.selp.model.data.UserInfo

object UserService {

    private lateinit var userRestService: UserRestService
    private var checkRefreshToken: () -> Boolean = { true }

    fun init(
        userRestService: UserRestService,
        checkRefreshToken: () -> Boolean
    ) {
        this.userRestService = userRestService
        this.checkRefreshToken = checkRefreshToken
    }

    suspend fun getUserInfo(): ResultOf<UserInfo> {
        refreshTokens()
        return try {
            val response = userRestService.getInfoUser()
            ResultOf.Success(UserInfo.from(response))
        } catch (ex: Exception) {
            ResultOf.Error(ex)
        }
    }

    private suspend fun refreshTokens() {
        if (checkRefreshToken()) {
            AuthService.refreshToken()
        }
    }
}