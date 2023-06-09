package com.mter.selp.model.data

import com.mter.selp.data.db.entities.SleepStat

data class Sleep(
    val id: Long,
    val createDate: Long,
    val timeSleepInHour: Float
) {
    companion object{
        fun from(sleep: Sleep): SleepStat {
            return SleepStat(sleep.id, sleep.createDate, sleep.timeSleepInHour)
        }
        fun to(sleepStat: SleepStat): Sleep {
            return Sleep(sleepStat.id, sleepStat.createDate, sleepStat.timeSleepInHour)
        }
    }
}