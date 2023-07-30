package com.mter.selp.model

import com.mter.selp.data.db.SelpDatabase
import com.mter.selp.model.data.FailedRequest
import com.mter.selp.model.data.Mood

object FailedRequestToServerRepository {

    private lateinit var database: SelpDatabase

    fun initRepository(database: SelpDatabase){
        this.database = database
    }

    suspend fun getFailedRequests(): List<FailedRequest>{
        return database.getFailedRequestToServerDao().getAllFailedRequest().map { FailedRequest.to(it) }
    }

    suspend fun saveFailedRequest(failedRequest: FailedRequest){
        database.getFailedRequestToServerDao().insertFailedRequest(FailedRequest.from(failedRequest))
    }

    suspend fun updateFailedRequest(failedRequest: FailedRequest){
        database.getFailedRequestToServerDao().updateFailedRequest(FailedRequest.from(failedRequest))
    }

    suspend fun deleteFailedRequest(failedRequest: FailedRequest){
        database.getFailedRequestToServerDao().deleteFailedRequest(failedRequest.idInDB, failedRequest.tableName)
    }
}