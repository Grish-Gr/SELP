package com.mter.selp.model

import com.mter.selp.data.network.AuthRestService
import com.mter.selp.data.network.request.LoginRequest
import com.mter.selp.data.network.request.RegistrationRequest
import com.mter.selp.data.network.request.Sex
import com.mter.selp.data.network.response.JwtResponse
import com.mter.selp.model.data.UserInfo
import java.util.Date

object AuthService {

    private lateinit var authRestService: AuthRestService
    private var successLogin: (jwtResponse: JwtResponse) -> Unit = {}
    private var getRefreshToken: () -> String = {""}
    private var successRefreshTokens: (jwtResponse: JwtResponse) -> Unit = {}

    fun init(
        authRestService: AuthRestService,
        successLogin: (jwtResponse: JwtResponse) -> Unit,
        getRefreshToken: () -> String,
        successRefreshTokens: (jwtResponse: JwtResponse) -> Unit
    ) {
        this.authRestService = authRestService
        this.successLogin = successLogin
        this.getRefreshToken = getRefreshToken
        this.successRefreshTokens = successRefreshTokens
    }

    suspend fun registration(
        username: String,
        sex: Sex,
        birthdate: Long,
        email: String,
        password: String
    ): ResultOf<UserInfo> {
        return try {
            val response = authRestService.registration(RegistrationRequest(
                username, sex.name, birthdate, email, password
            ))
            ResultOf.Success(UserInfo.from(response))
        } catch (ex: Exception) {
            ResultOf.Error(ex)
        }
    }

    suspend fun login(email: String, password: String): ResultOf<String> {
        return try {
            val response = authRestService.login(LoginRequest(email, password))
            successLogin(response)
            ResultOf.Success("Success")
        } catch (ex: Exception) {
            ResultOf.Error(ex)
        }
    }

    suspend fun refreshToken(): ResultOf<Boolean> {
        return try {
            val response = authRestService.refreshToken(getRefreshToken())
            successRefreshTokens(response)
            ResultOf.Success(true)
        } catch (ex: Exception) {
            ResultOf.Error(ex)
        }
    }
}