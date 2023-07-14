package com.mter.selp.data.network

import com.mter.selp.data.network.response.UserResponse
import retrofit2.http.GET

interface UserRestService {
    @GET("api/v1/user/info")
    suspend fun getInfoUser(): UserResponse
}