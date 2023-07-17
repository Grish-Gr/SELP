package com.mter.selp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mter.selp.data.db.entities.SleepStat

@Dao
interface SleepStatDao {

    @Query("SELECT * FROM stat_sleep")
    suspend fun getAllMoodStats(): List<SleepStat>

    @Query("SELECT * FROM stat_sleep WHERE (:startDate < stat_sleep.create_date AND stat_sleep.create_date < :endDate)")
    suspend fun getMoodStats(startDate: Long, endDate: Long): List<SleepStat>

    @Insert
    suspend fun insertSleepStat(sleepStat: SleepStat)

    @Query("DELETE FROM stat_sleep WHERE stat_sleep.id = :id")
    suspend fun deleteSleepStat(id: Long)

    @Update
    suspend fun updateSleepStat(sleepStat: SleepStat)
}