package com.mter.selp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stat_sleep")
data class SleepStat (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "create_date")
    val createDate: Long,

    @ColumnInfo(name = "time_sleep")
    val timeSleepInHour: Float
)