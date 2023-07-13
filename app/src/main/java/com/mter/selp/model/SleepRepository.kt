package com.mter.selp.model

import com.mter.selp.data.db.SelpDatabase

object SleepRepository {
    private lateinit var database: SelpDatabase

    fun initDatabase(database: SelpDatabase){
        this.database = database
    }

    suspend fun getSleeps(): List<Sleep>{
        return database.getSleepDao().getAllMoodStats().map { Sleep.to(it) }
    }

    suspend fun getSleeps(startDate: Long, endDate: Long): List<Sleep>{
        return database.getSleepDao().getMoodStats(startDate, endDate).map { Sleep.to(it) }
    }

    suspend fun save(sleep: Sleep){
        database.getSleepDao().insertSleepStat(Sleep.from(sleep))
    }

    suspend fun update(sleep: Sleep){
        database.getSleepDao().updateSleepStat(Sleep.from(sleep))
    }

    suspend fun deleteSleep(sleep: Sleep){
        database.getSleepDao().deleteSleepStat(sleep.id)
    }
}