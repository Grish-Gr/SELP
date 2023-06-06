package com.mter.selp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mter.selp.data.entities.MoodStat
import com.mter.selp.data.entities.SleepStat

@Database(entities = [MoodStat::class, SleepStat::class], version = 1)
abstract class SelpDatabase: RoomDatabase() {
    abstract fun getSleepDao(): SleepStatDao
    abstract fun getMoodDao(): MoodDao
}