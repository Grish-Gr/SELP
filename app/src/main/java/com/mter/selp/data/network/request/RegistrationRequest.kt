package com.mter.selp.data.network.request

import com.google.gson.annotations.SerializedName
import java.util.Date

data class RegistrationRequest(

    @SerializedName("username")
    val username: String,

    @SerializedName("sex")
    val sex: String,

    @SerializedName("birthdate")
    val birthdate: Long,

    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String
) {
}