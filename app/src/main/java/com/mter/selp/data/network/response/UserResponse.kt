package com.mter.selp.data.network.response

import com.google.gson.annotations.SerializedName
import java.util.Date

data class UserResponse(
    @SerializedName("userID")
    val userID: Long,

    @SerializedName("username")
    val username: String,

    @SerializedName("birthdate")
    val birthdate: Date,

    @SerializedName("sex")
    val sex: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("role")
    val role: String,

    @SerializedName("verificationInSystem")
    val verificationInSystem: Boolean
) {

}
