package com.mter.selp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mter.selp.data.db.entities.FailedRequestToServer
import com.mter.selp.data.db.entities.ResultTestStat
import com.mter.selp.data.db.entities.MoodStat
import com.mter.selp.data.db.entities.SleepStat

@Database(entities = [MoodStat::class, SleepStat::class, ResultTestStat::class, FailedRequestToServer::class], version = 1)
abstract class SelpDatabase: RoomDatabase() {
    abstract fun getSleepDao(): SleepStatDao
    abstract fun getMoodDao(): MoodDao
    abstract fun getResultTestDao(): ResultTestDao
    abstract fun getFailedRequestToServerDao(): FailedRequestToServerDao
}