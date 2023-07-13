package com.mter.selp.data.network

import com.mter.selp.data.network.request.LoginRequest
import com.mter.selp.data.network.request.RegistrationRequest
import com.mter.selp.data.network.response.JwtResponse
import com.mter.selp.data.network.response.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthRestService {

    @POST("/api/v1/auth/registration")
    suspend fun registration(@Body registrationRequest: RegistrationRequest): UserResponse

    @POST("/api/v1/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): JwtResponse

    @GET("/api/v1/refresh")
    suspend fun refreshToken(@Query("token") token: String): JwtResponse

}