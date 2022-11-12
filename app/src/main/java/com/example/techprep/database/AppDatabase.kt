package com.example.techprep.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.techprep.recordDatabase.RecordDao

@Database(entities = [QuestionsEntity::class], version = 1)
@TypeConverters(ListConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun questionDao(): QuestionsDao
    abstract fun recordsDao(): RecordDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        @Volatile
        private var RECORDS_INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context, false).also { INSTANCE = it }
            }

        fun getRecordsInstance(context: Context): AppDatabase =
            RECORDS_INSTANCE ?: synchronized(this) {
                RECORDS_INSTANCE ?: buildDatabase(context, true).also { RECORDS_INSTANCE = it }
            }

        private fun buildDatabase(context: Context, records: Boolean): AppDatabase{
            return if(records){
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "Records-db"
                ).build()
            }else{
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "Questions-db"
                ).build()
            }
        }
    }
}