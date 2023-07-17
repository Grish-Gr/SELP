package com.mter.selp.model.data

import com.google.gson.annotations.SerializedName
import com.mter.selp.data.network.response.UserResponse
import java.util.Date

data class UserInfo(
    val userID: Long,
    val username: String,
    val birthdate: Date,
    val sex: String,
    val email: String,
    val role: String,
    val verificationInSystem: Boolean
) {
    companion object {
        fun from(userResponse: UserResponse): UserInfo {
            return UserInfo(
                userResponse.userID,
                userResponse.username,
                userResponse.birthdate,
                userResponse.sex,
                userResponse.email,
                userResponse.role,
                userResponse.verificationInSystem
            )
        }
    }
}