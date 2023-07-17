package com.mter.selp.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stat_result_test")
data class ResultTestStat(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "type_test")
    val resultTestType: Int,

    @ColumnInfo(name = "test_result")
    val resultTestState: String

)