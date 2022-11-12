package com.example.techprep.questionList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.techprep.R
import com.example.techprep.database.QuestionsApplication
import com.example.techprep.database.QuestionsEntity
import com.example.techprep.topics.QUESTION_TAG
import com.example.techprep.topics.Topic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class QuestionListActivity : AppCompatActivity() {
    private var questionsList = mutableListOf<Question>()
    private lateinit var questionRecyclerView: RecyclerView
    private lateinit var topicTv: TextView
    private lateinit var topic: Topic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_list)

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
            (application as QuestionsApplication).db.questionDao().getCategoryQuestions(topic.name).collect() { questionEntity ->
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

    }
}