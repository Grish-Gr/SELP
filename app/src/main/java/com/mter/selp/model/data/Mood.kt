package com.mter.selp.model.data

import com.mter.selp.data.db.entities.MoodStat


data class Mood(
    val id: Long,
    val moodState: Float,
    val date: Long
) {
    companion object{
        fun from(mood: Mood): MoodStat {
            return MoodStat(mood.id, mood.moodState, mood.date)
        }
        fun to(moodStat: MoodStat): Mood {
            return Mood(moodStat.id, moodStat.moodState, moodStat.date)
        }
    }
}