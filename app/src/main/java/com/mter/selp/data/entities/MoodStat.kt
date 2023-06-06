package com.mter.selp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stat_mood")
data class MoodStat(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "mood_state")
    val moodState: Byte,

    @ColumnInfo(name = "date")
    val date: Long
)