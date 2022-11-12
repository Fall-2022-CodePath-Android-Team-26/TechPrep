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
import com.example.techprep.database.QuestionsEntity
import com.example.techprep.questionList.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

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
        val view = inflater.inflate(R.layout.fragment_topic_list, container, false)

        val layoutManager = LinearLayoutManager(context)
        topicsRecyclerView = view.findViewById(R.id.topic_recycler_view)
        topicsRecyclerView.layoutManager = layoutManager
        topicsRecyclerView.setHasFixedSize(true)
        topicAdapter = TopicAdapter(view.context, topics)
        topicsRecyclerView.adapter = topicAdapter

        lifecycleScope.launch(Dispatchers.IO) {
            (activity?.application as QuestionsApplication).db.questionDao().deleteAll()
        }

        // Update the return statement to return the inflated view from above
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Call the new method within onViewCreated
        val category = listOf<String>("Linux", "DevOps", "Kubernetes", "Docker", "Code", "MySQL",
        "HTML", "BASH")
        for(i in category){
            fetchQuestions(i)
        }
        fetchTopics(category)
    }

    private fun fetchTopics(category: List<String>) {
        for(i in category){
            topics.add(Topic(i))
        }
        topicAdapter.notifyDataSetChanged()
    }

    private fun fetchQuestions(category: String?) {
        val questionEntityList = mutableListOf<QuestionsEntity>()
        val params = RequestParams()
        params["apiKey"] = SEARCH_API_KEY
        params["category"] =  category
        val client = AsyncHttpClient()
        client.get(TOPIC_SEARCH_URL, params, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch questions: $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
//                Log.i(TAG, "Successfully fetched questions: $json")
                try {
                    val jsonResponse: JSONArray = json.jsonArray as JSONArray
                    val questionsRawJSON: String = jsonResponse.toString()
                    val questionsEntityList = mutableListOf<QuestionsEntity>()
                    for (i in 0 until jsonResponse.length()) {
                        val responseObject = jsonResponse[i] as JSONObject
                        //Log.i("json response", response.toString())

                        // Get key:value pairs from json answer field
                        val responseAnswersObject = responseObject.get("answers") as JSONObject
                        val answersArray = getJSONPairList(responseAnswersObject)

                        // Get key:value pairs from json correct answers field
                        val responseCorrectAnswersObject = responseObject.get("correct_answers") as JSONObject
                        val correctAnswersArray = getJSONPairList(responseCorrectAnswersObject)

                        val questionEntity = QuestionsEntity(
                            id = responseObject.get("id").toString(),
                            question = responseObject.get("question").toString(),
                            description = responseObject.get("description").toString(),
                            answers = answersArray,
                            multiple_correct_answers = responseObject.get("multiple_correct_answers").toString(),
                            correct_answers = correctAnswersArray,
                            explanation = responseObject.get("explanation").toString(),
                            tip = responseObject.get("tip").toString(),
                            tags = responseObject.get("tags").toString(),
                            category = responseObject.get("category").toString(),
                            difficulty = responseObject.get("difficulty").toString(),
                        )
                        questionsEntityList.add(questionEntity)
                    }

                    Log.i("Database", questionsRawJSON)
                    // Insert questions into database
                    lifecycleScope.launch(Dispatchers.IO) {
                        Log.i("DB response", "This is being repeated")
                        (activity?.application as QuestionsApplication).db.questionDao().insertAll(questionsEntityList)
                    }

                } catch (e: JSONException) {
                    Log.e(TAG, "Exception: $e")
                }
            }

        })

    }

    private fun getJSONPairList(dataObject : JSONObject) : MutableList<QuestionJson> {
        val keyValueArray = mutableListOf<QuestionJson>()
        var keys = dataObject.keys()

        while(keys.hasNext()) {
            val key = keys.next()
            val value = dataObject.optString(key)
            val questionJson = QuestionJson(
                key = key,
                value = value
            )
            keyValueArray.add(questionJson)
        }
        return keyValueArray
    }

    companion object {
        fun newInstance(): TopicListFragment {
            return TopicListFragment()
        }
    }
}

// Example response
// {"id":77,
// "question":"Which one of these variables has an illegal name?",
// "description":null,
// "answers":{"answer_a":"$myVar","answer_b":"$my_Var","answer_c":"$my-Var","answer_d":"$MyVar","answer_e":null,"answer_f":null},
// "multiple_correct_answers":"false",
// "correct_answers":{"answer_a_correct":"false","answer_b_correct":"false","answer_c_correct":"true","answer_d_correct":"false","answer_e_correct":"false","answer_f_correct":"false"},
// "correct_answer":"answer_c",
// "explanation":null,
// "tip":null,
// "tags":[{"name":"PHP"}],
// "category":"",
// "difficulty":"Medium"}