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

    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout, actual item layout
        val contactView = inflater.inflate(R.layout.item_topic, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get the data model based on position
        val topic = topics[position]
        // Set item views based on your views and data model
        viewHolder.bind(topic)
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return topics.size
    }

    // Provide a direct reference to each of the views within a data item
    // ViewHolder" object which describes and provides access to all the views within each item row.
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
            // Get selected article
            val topic = topics[adapterPosition]

            // Navigate to Details screen and pass selected article
            val intent = Intent(context, QuestionListActivity::class.java)
            intent.putExtra(QUESTION_TAG, topic)
            context.startActivity(intent)
        }

    }
}
