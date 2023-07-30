package com.mter.selp.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "failed_request_server")
data class FailedRequestToServer(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "id_in_db")
    val idInDB: Long,

    @ColumnInfo(name = "table_name")
    val tableName: String
){}