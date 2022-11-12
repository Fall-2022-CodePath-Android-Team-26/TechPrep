package com.example.techprep.questionList

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.techprep.MultipleChoiceActivity
import com.example.techprep.database.QuestionJson
import com.example.techprep.databinding.ActivityQuestionListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuestionListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionListBinding
    private lateinit var chosenTopic: String
    private val questions = mutableListOf<QuestionJson>()
    private var mTotalScore: Int = 0
    private var mCompletedQuestion: Int = 0
    private var mQuestionsList: MutableList<QuestionJson>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionListBinding.inflate((layoutInflater))
        setContentView(binding?.root)

        chosenTopic = intent.getStringExtra("Chosen Topic").toString()

        val questionListAdapter = QuestionListAdapter(this, questions)
        binding?.rvQuestionList?.adapter = questionListAdapter
        binding?.rvQuestionList?.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            binding?.rvQuestionList?.addItemDecoration(dividerItemDecoration)
        }

        if (mCompletedQuestion == mQuestionsList?.size) {
            //Todo: Go back to Main Activity after completing all questions
        }

        var inputActivityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if(result.data?.getBooleanExtra("Correct") as Boolean){
                    mTotalScore += 1
                }
            }
        }

    }
}