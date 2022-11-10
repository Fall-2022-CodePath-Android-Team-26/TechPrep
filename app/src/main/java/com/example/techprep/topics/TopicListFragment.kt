package com.example.techprep.topics

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.techprep.BuildConfig
import com.example.techprep.R
import com.example.techprep.database.ListConverter
import com.example.techprep.database.QuestionJson
import com.example.techprep.database.QuestionsApplication
import com.example.techprep.database.QuestionsEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import okhttp3.Headers
import org.json.JSONArray

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
        // Inflate the layout for this fragment - recyclerView layout
        val view = inflater.inflate(R.layout.fragment_topic_list, container, false)

        val layoutManager = LinearLayoutManager(context)
        topicsRecyclerView = view.findViewById(R.id.topic_recycler_view)
        topicsRecyclerView.layoutManager = layoutManager
        topicsRecyclerView.setHasFixedSize(true)
        topicAdapter = TopicAdapter(view.context, topics)
        topicsRecyclerView.adapter = topicAdapter

        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = BuildConfig.API_KEY
        val url = "https://quizapi.io/api/v1/questions"

        // Using the client, perform the HTTP request
        client[url, params, object : JsonHttpResponseHandler(){
            /*
             * The onSuccess function gets called when
             * HTTP response status is "200 OK"
             */
            override fun onSuccess(
                statusCode: Int,
                headers: Headers,
                json: JSON
            ) {
                // The wait for a response is over

                val resultsJSON : JSONArray = json.jsonObject.get("results") as JSONArray

                val gson = Gson()
                val arrayQuestionType = object : TypeToken<List<Topic>>() {}.type

                val models : List<Topic> = gson.fromJson(resultsJSON.toString(), arrayQuestionType)

                // Look for this in Logcat:
                Log.d("TopicListFragment", "response successful")
            }

            /*
             * The onFailure function gets called when
             * HTTP response status is "4XX" (eg. 401, 403, 404)
             */
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                t: Throwable?
            ) {
                // The wait for a response is over
                // If the error is not null, log it!
                t?.message?.let {
                    Log.e("TopicListFragment", errorResponse)
                }
            }
        }]

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