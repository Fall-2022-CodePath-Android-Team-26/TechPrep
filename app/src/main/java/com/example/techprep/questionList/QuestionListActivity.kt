package com.example.techprep.questionList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.techprep.R
import com.example.techprep.database.QuestionJson

class QuestionListActivity : AppCompatActivity() {

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: MutableList<QuestionJson>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_list)


    }
}