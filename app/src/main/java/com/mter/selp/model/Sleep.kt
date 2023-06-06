package com.mter.selp.model

import com.mter.selp.data.entities.MoodStat
import com.mter.selp.data.entities.SleepStat

data class Sleep(
    val id: Long,
    val createDate: Long,
    val goingToBedTime: Long,
    val awakeTime: Long
) {
    companion object{
        fun from(sleep: Sleep): SleepStat{
            return SleepStat(sleep.id, sleep.createDate, sleep.goingToBedTime, sleep.awakeTime)
        }
        fun to(sleepStat: SleepStat): Sleep{
            return Sleep(sleepStat.id, sleepStat.createDate, sleepStat.goingToBedTime, sleepStat.awakeTime)
        }
    }
}