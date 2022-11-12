package com.example.techprep.recordDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RecordEntity::class], version = 1)
abstract class RecordAppDatabase : RoomDatabase() {

    abstract fun recordDao(): RecordDao

    companion object {

        @Volatile
        private var INSTANCE: RecordAppDatabase? = null

        fun getInstance(context: Context): RecordAppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                RecordAppDatabase::class.java, "Records-db"
            ).build()
    }
}