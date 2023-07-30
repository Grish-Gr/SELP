package com.mter.selp.data.db

import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mter.selp.data.db.entities.FailedRequestToServer
import com.mter.selp.data.db.entities.MoodStat

@Dao
interface FailedRequestToServerDao {

    @Query("SELECT * FROM failed_request_server")
    suspend fun getAllFailedRequest(): List<FailedRequestToServer>

    @Insert
    suspend fun insertFailedRequest(failedRequest: FailedRequestToServer)

    @Query("DELETE FROM failed_request_server WHERE failed_request_server.id_in_db = :idInDB AND failed_request_server.table_name = :tableName")
    suspend fun deleteFailedRequest(idInDB: Long, tableName: String)

    @Update
    suspend fun updateFailedRequest(failedRequest: FailedRequestToServer)
}