package com.example.techprep.recordDatabase

import android.app.Application
import com.example.techprep.database.AppDatabase

class RecordsApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}