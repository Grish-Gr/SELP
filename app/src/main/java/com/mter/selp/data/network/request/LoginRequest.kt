package com.mter.selp.data.network.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(

    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String
)
