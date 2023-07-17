package com.mter.selp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mter.selp.data.db.entities.ResultTestStat

@Dao
interface ResultTestDao {

    @Query("SELECT * FROM stat_result_test")
    suspend fun getAllResultTests(): List<ResultTestStat>

    @Insert
    suspend fun insertResultTestStat(resultTestStat: ResultTestStat)

    @Query("DELETE FROM stat_result_test WHERE stat_result_test.id = :id")
    suspend fun deleteResultTestStat(id: Long)

    @Update
    suspend fun updateResultTestStat(resultTestStat: ResultTestStat)
}