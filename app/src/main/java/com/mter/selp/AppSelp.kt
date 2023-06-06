package com.mter.selp

import android.app.Application
import androidx.room.Room
import com.mter.selp.data.SelpDatabase
import com.mter.selp.model.MoodRepository
import com.mter.selp.model.SleepRepository

class AppSelp: Application() {
    override fun onCreate() {
        super.onCreate()
        val database = Room.databaseBuilder(this, SelpDatabase::class.java, "selp_database")
            .build()
        MoodRepository.initRepository(database)
        SleepRepository.initDatabase(database)
    }
}