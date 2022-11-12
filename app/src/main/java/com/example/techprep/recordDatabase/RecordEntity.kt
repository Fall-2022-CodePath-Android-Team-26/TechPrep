package com.example.techprep.recordDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "record_table")
data class RecordEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "score") val score: Int?,
    @ColumnInfo(name = "maxScore") val maxScore: Int?,
    @ColumnInfo(name = "category") val category: String?
)