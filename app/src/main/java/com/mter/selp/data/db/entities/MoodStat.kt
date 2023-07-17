package com.mter.selp.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stat_mood")
data class MoodStat(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "mood_state")
    val moodState: Float,

    @ColumnInfo(name = "date")
    val date: Long
)