package com.example.techprep.database

import android.app.Application

class QuestionsApplication : Application(){
    val db by lazy { AppDatabase.getInstance(this) }
}