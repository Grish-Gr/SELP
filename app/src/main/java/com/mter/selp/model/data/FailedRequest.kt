package com.mter.selp.model.data

import com.mter.selp.data.db.entities.FailedRequestToServer
import com.mter.selp.data.db.entities.MoodStat

data class FailedRequest(
    val tableName: String,
    val idInDB: Long
) {
    companion object {

        fun from(failedRequest: FailedRequest): FailedRequestToServer {
            return FailedRequestToServer(1L, failedRequest.idInDB, failedRequest.tableName)
        }
        fun to(failedRequest: FailedRequestToServer): FailedRequest {
            return FailedRequest(failedRequest.tableName, failedRequest.idInDB)
        }
    }
}