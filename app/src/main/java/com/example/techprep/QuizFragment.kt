package com.example.techprep
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray

private const val API_KEY = BuildConfig.API_KEY

class QuizFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_main, container, false)
//        val recyclerView = view.findViewById<View>(R.id.questionList) as RecyclerView
        val context = view.context
//        recyclerView.layoutManager = LinearLayoutManager(context)
//        updateAdapter(progressBar, recyclerView)
        return view
    }

    private fun updateAdapter(recyclerView: RecyclerView) {

        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = API_KEY
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
//                val arrayQuestionType = object : TypeToken<List<Questions>>() {}.type

//                val models : List<Questions> = gson.fromJson(resultsJSON.toString(), arrayQuestionType)
//                val adapter = QuestionsAdapter(models);
//                recyclerView.adapter = adapter

                // Look for this in Logcat:
                Log.d("QuizFragment", "response successful")
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
                    Log.e("QuizFragment", errorResponse)
                }
            }
        }]

    }
}