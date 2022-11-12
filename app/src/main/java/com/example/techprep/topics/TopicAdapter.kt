package com.example.techprep.topics

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.techprep.R
import com.example.techprep.questionList.QuestionListActivity

private const val QUESTION_TAG = "Question List"

class TopicAdapter(private val context: Context, private val topics: List<Topic>) :
    RecyclerView.Adapter<TopicAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_topic, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val topic = topics[position]
        viewHolder.bind(topic)
    }

    override fun getItemCount(): Int {
        return topics.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val nameTextView = itemView.findViewById<TextView>(R.id.topic_name)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(topic: Topic) {
            nameTextView.text = topic.name
        }

        override fun onClick(v: View?) {
            val topicName = topics[absoluteAdapterPosition].name

            // Navigate to Details screen and pass selected article
            val intent = Intent(context, QuestionListActivity::class.java)
            intent.putExtra(QUESTION_TAG, topic)
            context.startActivity(intent)
        }

    }
}
