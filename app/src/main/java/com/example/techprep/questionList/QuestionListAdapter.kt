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
import com.example.techprep.database.QuestionJson

class QuestionListAdapter (private val context: Context, private val questions: List<QuestionJson>) :

    RecyclerView.Adapter<QuestionListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_question_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val question = questions[position]
        holder.bind(question)
    }

    override fun getItemCount() =  questions.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private var tvId: TextView = itemView.findViewById(R.id.tv_date)
        private var tvQuestion: TextView = itemView.findViewById(R.id.tv_question)
        private var tvDescription: TextView = itemView.findViewById(R.id.tv_description)
        private var tvDifficulty: TextView = itemView.findViewById(R.id.tv_difficulty)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val question = questions[absoluteAdapterPosition]
            val intent = Intent(context, MultipleChoiceActivity::class.java)
            //intent.putExtra("Chosen Question", question)
            context.startActivity(intent)
        }

        fun bind(question: QuestionJson) {
            tvId.text = "1"
            tvQuestion.text = "What is Array?"
            tvDifficulty.text = "Easy"
            tvDescription.text = "description"
        }

    }
}