package com.example.techprep.questionList

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.techprep.MultipleChoiceActivity
import com.example.techprep.R

const val QUESTION_EXTRA = "QUESTION_EXTRA"

class QuestionListAdapter (private val context: Context, private val questionList: List<Question>) :
    RecyclerView.Adapter<QuestionListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_question_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val question = questionList[position]
        holder.bind(question)
    }

    override fun getItemCount() =  questionList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private var questionId: TextView = itemView.findViewById(R.id.question_id)
        private var questionTitle: TextView = itemView.findViewById(R.id.question_title)
        private var questionDifficulty: TextView = itemView.findViewById(R.id.question_difficulty)
        private var questionDescr: TextView = itemView.findViewById(R.id.question_description)


        init {
            itemView.setOnClickListener(this)
        }

        fun bind(question: Question) {
            questionId.text = question.id
            questionTitle.text = question.question
            questionDifficulty.text = question.difficulty
            questionDescr.text = question.description
        }

        override fun onClick(v: View?) {
            // Get selected question
            val question = questionList[adapterPosition]

            // Navigate to Details screen and pass selected question
            val intent = Intent(context, MultipleChoiceActivity::class.java)
            intent.putExtra(QUESTION_EXTRA, question)
            context.startActivity(intent)
        }


    }
}