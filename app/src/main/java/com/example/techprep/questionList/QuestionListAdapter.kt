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
import com.example.techprep.R

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

        private var tvId: TextView = itemView.findViewById(R.id.tv_date)
        private var tvQuestion: TextView = itemView.findViewById(R.id.tv_question)
        private var tvDescription: TextView = itemView.findViewById(R.id.tv_description)
        private var tvDifficulty: TextView = itemView.findViewById(R.id.tv_difficulty)

        init {
            itemView.setOnClickListener(this)
        }

        // TODO: Write a helper method to help set up the onBindViewHolder method

        override fun onClick(v: View?) {
            // TODO: Get selected article
            val movie = questionList[absoluteAdapterPosition]
            // TODO: Navigate to Details screen and pass selected article
            val intent = Intent(context, TopRatedMovieDetailsActivity::class.java)
            intent.putExtra(TOP_RATED_MOVIE_EXTRA, movie)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, (poster as View?)!!, "poster")
            context.startActivity(intent, options.toBundle())
        }

        fun bind(question: Question) {
            tvId.text = question.title
            val radius = 40; // corner radius, higher value = more rounded
            val margin = 10; // crop margin, set to 0 for corners with no crop
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w500${movie.poster}")
                .placeholder(R.drawable.ic_baseline_image_24)
                .error(R.drawable.ic_baseline_image_not_supported_24)
                .apply(RequestOptions.bitmapTransform(RoundedCornersTransformation(radius,margin)))
                .into(poster)

        }
    }
}