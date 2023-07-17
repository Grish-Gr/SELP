package com.mter.selp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mter.selp.data.db.entities.ResultTestStat
import com.mter.selp.data.db.entities.MoodStat
import com.mter.selp.data.db.entities.SleepStat

@Database(entities = [MoodStat::class, SleepStat::class, ResultTestStat::class], version = 1)
abstract class SelpDatabase: RoomDatabase() {
    abstract fun getSleepDao(): SleepStatDao
    abstract fun getMoodDao(): MoodDao
    abstract fun getResultTestDao(): ResultTestDao
}