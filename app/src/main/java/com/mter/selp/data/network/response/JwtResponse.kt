package com.mter.selp.data.network.response

import com.google.gson.annotations.SerializedName

data class JwtResponse(

    @SerializedName("type")
    val type: String,

    @SerializedName("accessToken")
    val accessToken: String,

    @SerializedName("refreshToken")
    val refreshToken: String,

    @SerializedName("expireTime")
    val expireTime: Long,

    @SerializedName("role")
    val role: String,

    @SerializedName("paidSubscriptionCode")
    val paidSubscriptionCode: String,
) {
}