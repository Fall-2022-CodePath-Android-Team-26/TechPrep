package com.example.techprep.questionList

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.techprep.MultipleChoiceActivity
import com.example.techprep.R
import com.example.techprep.database.QuestionJson
import com.example.techprep.database.QuestionsApplication
import com.example.techprep.databinding.ActivityQuestionListBinding
import com.example.techprep.topics.QUESTION_TAG
import com.example.techprep.topics.Topic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuestionListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionListBinding
    private var mTotalScore: Int = 0
    private var mCompletedQuestion: Int = 0
    private var mQuestionsList: MutableList<QuestionJson>? = null

    private var questionsList = mutableListOf<Question>()
    private lateinit var questionRecyclerView: RecyclerView
    private lateinit var topicTv: TextView
    private lateinit var topic: Topic


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionListBinding.inflate((layoutInflater))
        setContentView(binding?.root)

//        chosenTopic = intent.getStringExtra("Chosen Topic").toString()
//
//        binding?.rvQuestionList?.adapter = questionListAdapter
//        binding?.rvQuestionList?.layoutManager = LinearLayoutManager(this).also {
//            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
//            binding?.rvQuestionList?.addItemDecoration(dividerItemDecoration)
//        }

        val adapter = QuestionListAdapter(this, questionsList)
        questionRecyclerView = findViewById<View>(R.id.rv_question_list) as RecyclerView
        topicTv = findViewById<TextView>(R.id.tv_question_list)
        topic = intent.getSerializableExtra(QUESTION_TAG) as Topic

        topicTv.text = topic.name

        // Attach the adapter to the recyclerview to populate items
        questionRecyclerView.adapter = adapter
        // Set layout manager to position the items
        questionRecyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            (application as QuestionsApplication).db.questionDao().getCategoryQuestions(topic.name)
                .collect() { questionEntity ->
                    questionEntity.map { entity ->
                        Question(
                            entity.id,
                            entity.question,
                            entity.description,
                            entity.answers,
                            entity.multiple_correct_answers,
                            entity.correct_answers,
                            entity.explanation,
                            entity.tip,
                            entity.tags,
                            entity.category,
                            entity.difficulty
                        )
                    }.also { mappedList ->
                        questionsList.clear()
                        questionsList.addAll(mappedList)
                        adapter.notifyDataSetChanged()
                    }
                }
        }

        if (mCompletedQuestion == mQuestionsList?.size) {
            //Todo: Go back to Main Activity after completing all questions
        }

//        var inputActivityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
//            ActivityResultContracts.StartActivityForResult()
//        ) { result ->
//            if (result.resultCode == Activity.RESULT_OK) {
//                if(result.data?.getBooleanExtra("Correct") as Boolean){
//                    mTotalScore += 1
//                }
//            }
//        }

    }
}