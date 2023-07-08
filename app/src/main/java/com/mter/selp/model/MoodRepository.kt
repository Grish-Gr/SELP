package com.mter.selp.model

import com.mter.selp.data.db.SelpDatabase

object MoodRepository {

    private lateinit var database: SelpDatabase

    fun initRepository(database: SelpDatabase){
        this.database = database
    }

    suspend fun getMoods(startDate: Long, endDate: Long): List<Mood>{
        return database.getMoodDao().getMoodStats(startDate, endDate).map { Mood.to(it) }
    }

    suspend fun getMoods(): List<Mood>{
        return database.getMoodDao().getAllMoodStats().map { Mood.to(it) }
    }

    suspend fun saveMood(mood: Mood){
        database.getMoodDao().insertMoodStat(Mood.from(mood))
    }

    suspend fun updateMood(mood: Mood){
        database.getMoodDao().updateMoodStat(Mood.from(mood))
    }

    suspend fun deleteMood(mood: Mood){
        database.getMoodDao().deleteMoodStat(mood.id)
    }
}