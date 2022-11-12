package com.example.techprep.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionsDao {
    @Query("SELECT * FROM questions_table")
    fun getAll(): Flow<List<QuestionsEntity>>

    @Query("SELECT * FROM questions_table WHERE category = :category")
    fun getCategoryQuestions(category: String?) : Flow<List<QuestionsEntity>>

    @Insert
    fun insertAll(questions: List<QuestionsEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(question: QuestionsEntity)

    @Query("DELETE FROM questions_table")
    fun deleteAll()
}