package com.mter.selp.data.network

import com.mter.selp.data.network.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserRestService {
    @GET("api/v1/user/info")
    suspend fun getInfoUser(): UserResponse

    @GET("api/v1/user/permission-admin")
    suspend fun getAdminPermission(@Query("secretKey") secretKey: String): UserResponse
}