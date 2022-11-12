package com.example.techprep.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.techprep.R
import com.example.techprep.topics.Topic
import com.example.techprep.topics.TopicAdapter
import com.example.techprep.topics.TopicListFragment
import com.example.techprep.utils.Constants
import java.util.ArrayList


class DashboardFragment : Fragment() {
    private lateinit var records : ArrayList<Record>
    private lateinit var rvHistory: RecyclerView
    private lateinit var historyAdapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment - recyclerView layout
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        records = Constants.getRecords()

        val layoutManager = LinearLayoutManager(context)
        rvHistory = view.findViewById(R.id.rv_history)
        rvHistory.layoutManager = layoutManager
        rvHistory.setHasFixedSize(true)
        historyAdapter = HistoryAdapter(view.context, records)
        rvHistory.adapter = historyAdapter

        // Update the return statement to return the inflated view from above
        return view
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        // Call the new method within onViewCreated
//        fetchRecords()
//    }
//
//    private fun fetchRecords() {
//
//    }

    companion object {
        fun newInstance(): DashboardFragment {
            return DashboardFragment()
        }
    }
}