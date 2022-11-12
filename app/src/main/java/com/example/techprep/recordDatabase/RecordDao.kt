package com.example.techprep.recordDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.techprep.database.QuestionsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {
    @Query("SELECT * FROM record_table")
    fun getAll(): Flow<List<RecordEntity>>

    @Insert
    fun insertAll(records: List<RecordEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(records: RecordEntity)

    @Query("DELETE FROM record_table")
    fun deleteAll()
}