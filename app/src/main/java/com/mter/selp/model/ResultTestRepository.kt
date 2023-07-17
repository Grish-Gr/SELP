package com.mter.selp.model

import com.mter.selp.data.SelpDatabase

object ResultTestRepository {
    private lateinit var database: SelpDatabase

    fun initDatabase(database: SelpDatabase){
        this.database = database
    }

    suspend fun getResultTests(): List<ResultTest>{
        return database.getResultTestDao().getAllResultTests().map { ResultTest.to(it) }
    }

    suspend fun saveResultTests(resultTest: ResultTest){
        database.getResultTestDao().insertResultTestStat(ResultTest.from(resultTest))
    }

    suspend fun updateResultTests(resultTest: ResultTest){
        database.getResultTestDao().updateResultTestStat(ResultTest.from(resultTest))
    }

    suspend fun deleteResultTests(resultTest: ResultTest){
        database.getResultTestDao().deleteResultTestStat(resultTest.id)
    }
}