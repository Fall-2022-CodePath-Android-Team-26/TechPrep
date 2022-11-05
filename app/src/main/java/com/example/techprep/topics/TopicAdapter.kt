package com.example.techprep.topics

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.techprep.R

class TopicAdapter(private val context: Context, private val topics: List<Topic>) :
    RecyclerView.Adapter<TopicAdapter.ViewHolder>(){

    // Provide a direct reference to each of the views within a data item
    // ViewHolder" object which describes and provides access to all the views within each item row.
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView : TextView

        init {
            nameTextView = itemView.findViewById(R.id.topic_name)
        }
    }


    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout, actual item layout
        val contactView = inflater.inflate(R.layout.item_topic, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: TopicAdapter.ViewHolder, position: Int) {
        // Get the data model based on position
        val topic = topics.get(position)
        // Set item views based on your views and data model
        viewHolder.nameTextView.text = topic.name
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return topics.size
    }
}