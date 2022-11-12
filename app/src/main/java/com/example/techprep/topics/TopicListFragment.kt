package com.example.techprep.topics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.techprep.BuildConfig
import com.example.techprep.R

private const val SEARCH_API_KEY = BuildConfig.API_KEY
private const val TOPIC_SEARCH_URL = " =${SEARCH_API_KEY}"

class TopicListFragment : Fragment() {
    private val topics = mutableListOf<Topic>()
    private lateinit var topicsRecyclerView: RecyclerView
    private lateinit var topicAdapter: TopicAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_topic_list, container, false)

        val layoutManager = LinearLayoutManager(context)
        topicsRecyclerView = view.findViewById(R.id.topic_recycler_view)
        topicsRecyclerView.layoutManager = layoutManager
        topicsRecyclerView.setHasFixedSize(true)
        topicAdapter = TopicAdapter(view.context, topics)
        topicsRecyclerView.adapter = topicAdapter

        // Update the return statement to return the inflated view from above
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Call the new method within onViewCreated
        fetchTopics()
    }

    private fun fetchTopics() {

    }

    companion object {
        fun newInstance(): TopicListFragment {
            return TopicListFragment()
        }
    }
}