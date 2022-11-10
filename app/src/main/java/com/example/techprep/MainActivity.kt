package com.example.techprep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.techprep.custom.CustomFragment
import com.example.techprep.dashboard.DashboardFragment
import com.example.techprep.databinding.ActivityMainBinding
import com.example.techprep.topics.TopicListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding?.root)

        val fragmentManager: FragmentManager = supportFragmentManager

        // define your fragments here
        val topicListFragment: Fragment = TopicListFragment()
        val dashboardFragment: Fragment = DashboardFragment()
        val customFragment: Fragment = CustomFragment()

        // handle navigation selection
        binding?.bottomNavigation?.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.nav_topics -> fragment = topicListFragment
                R.id.nav_dashboard -> fragment = dashboardFragment
                R.id.nav_custom -> fragment = customFragment
            }
            replaceFragment(fragment)
            true
        }

        // Set default selection
        binding?.bottomNavigation?.selectedItemId = R.id.nav_topics
    }

    private fun replaceFragment(replacedFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.rl_container, replacedFragment)
        fragmentTransaction.commit()
    }
}