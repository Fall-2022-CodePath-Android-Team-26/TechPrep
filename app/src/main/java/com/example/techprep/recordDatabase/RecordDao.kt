package com.example.techprep.recordDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {
    @Query("SELECT * FROM record_table")
    fun getAll(): Flow<List<RecordEntity>>

    @Insert
    fun insertAll(records: List<RecordEntity>)

    @Insert
    fun insert(records: RecordEntity)

    @Query("DELETE FROM record_table")
    fun deleteAll()
}