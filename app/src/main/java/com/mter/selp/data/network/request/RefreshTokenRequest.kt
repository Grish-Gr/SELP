package com.mter.selp.data.network.request

import com.google.gson.annotations.SerializedName

data class RefreshTokenRequest(

    @SerializedName("refreshToken")
    val refreshToken: String
) {
}