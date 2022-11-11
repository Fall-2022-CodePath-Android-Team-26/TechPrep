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
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.techprep.BuildConfig
import com.example.techprep.R
import com.example.techprep.database.QuestionJson
import com.example.techprep.database.QuestionsApplication
import com.example.techprep.questionList.Question
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNull.serializer
import okhttp3.Headers
import org.json.JSONArray
import org.json.JSONException

private const val SEARCH_API_KEY = BuildConfig.API_KEY
//private const val TOPIC_SEARCH_URL = " =${SEARCH_API_KEY}"
private const val TOPIC_SEARCH_URL = "https://quizapi.io/api/v1/questions"
private const val TAG = "JSONResponse"

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

class TopicListFragment : Fragment() {
    private val topics = mutableListOf<Topic>()
    private val questionInfo = mutableListOf<Question>()
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

    private fun fetchQuestions() {
        val params = RequestParams()
        params["api_key"] = SEARCH_API_KEY
        val client = AsyncHttpClient()
        client.get(TOPIC_SEARCH_URL, params, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch articles: $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.i(TAG, "Successfully fetched articles: $json")
                try {
//                    // TODO: Create the parsedJSON
//                    val parsedJson = createJson().decodeFromString(
//                        TopicsResponse.serializer(),
//                        json.jsonArray.toString()
//                    )
//                    // TODO: Do something with the returned json (contains article information)
//                    parsedJson.response?.let { list ->
//                        topics.addAll(list)
//                        // TODO: Save the articles and reload the screen
//                        topicAdapter.notifyDataSetChanged()
//                    }
                    val jsonResponse: JSONArray = json.jsonArray as JSONArray
                    for(i in 0..jsonResponse.length()){
                        val jsonQuestion = jsonResponse.getJSONObject(i)
                    }

                } catch (e: JSONException) {
                    Log.e(TAG, "Exception: $e")
                }
            }

        })
        lifecycleScope.launch() {
            (activity?.application as QuestionsApplication).db.questionDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    Question(
                        entity.api_id,
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
                    questionInfo.clear()
                    questionInfo.addAll(mappedList)
//                    diaryAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    companion object {
        fun newInstance(): TopicListFragment {
            return TopicListFragment()
        }
    }
}