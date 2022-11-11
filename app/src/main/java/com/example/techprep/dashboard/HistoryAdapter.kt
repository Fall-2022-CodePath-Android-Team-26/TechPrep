package com.example.techprep.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.techprep.R


class HistoryAdapter (private val context: Context, private val records: List<Record>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val question = records[position]
        holder.bind(question)
    }

    override fun getItemCount() =  records.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private var tvTopics: TextView = itemView.findViewById(R.id.tv_topics)
        private var tvDate: TextView = itemView.findViewById(R.id.tv_date)
        private var tvScore: TextView = itemView.findViewById(R.id.tv_score)

        fun bind(record: Record) {
            tvTopics.text = record.category
            tvDate.text = record.date
            tvScore.text =  "${record.score}/${record.maxScore}"
        }
    }
}