package com.mter.selp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mter.selp.data.entities.MoodStat

@Dao
interface MoodDao {

    @Query("SELECT * FROM stat_mood")
    suspend fun getAllMoodStats(): List<MoodStat>

    @Query("SELECT * FROM stat_mood WHERE (:startDate < stat_mood.date AND stat_mood.date < :endDate)")
    suspend fun getMoodStats(startDate: Long, endDate: Long): List<MoodStat>

    @Insert
    suspend fun insertMoodStat(moodStat: MoodStat)

    @Query("DELETE FROM stat_mood WHERE stat_mood.id = :id")
    suspend fun deleteMoodStat(id: Long)

    @Update
    suspend fun updateMoodStat(moodStat: MoodStat)
}