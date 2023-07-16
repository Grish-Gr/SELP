package com.mter.selp.model

import com.mter.selp.data.entities.ResultTestStat


data class ResultTest(
    val id: Long,
    val type: Int,
    val result: String

) {
    companion object{
        fun from(resultTest: ResultTest): ResultTestStat{
            return ResultTestStat(resultTest.id, resultTest.type, resultTest.result)
        }
        fun to(resultTestStat: ResultTestStat): ResultTest{
            return ResultTest(resultTestStat.id, resultTestStat.resultTestType, resultTestStat.resultTestState)
        }
    }
}