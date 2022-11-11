package com.example.techprep.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions_table")
data class QuestionsEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "api_id") val api_id: String?,
    @ColumnInfo(name = "question") val question: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "answers") val answers: List<QuestionJson>?,
    @ColumnInfo(name = "multiple_correct_answers") val multiple_correct_answers: String?,
    @ColumnInfo(name = "correct_answers") val correct_answers:  List<QuestionJson>?,
    @ColumnInfo(name = "explanation") val explanation: String?,
    @ColumnInfo(name = "tip") val tip: String?,
    @ColumnInfo(name = "tags") val tags: String?,
    @ColumnInfo(name = "category") val category: String?,
    @ColumnInfo(name = "difficulty") val difficulty: String?) {
}