package com.example.restaurents


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.restaurants.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavBarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nav_bar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navView: BottomNavigationView = view.findViewById(R.id.bottom_navigation)

        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_search -> {
                    Log.d("NavBarFragment", "Search button clicked")
                    (activity as MainActivity).showFragment(SearchFragment::class.java)
                    true
                }

                R.id.navigation_recommendations -> {
                    Log.d("NavBarFragment", "Recommendations button clicked")
                    (activity as MainActivity).showFragment(RecommendationFragment::class.java)
                    true
                }

                R.id.navigation_map -> {
                    Log.d("NavBarFragment", "Map button clicked")
                    (activity as MainActivity).showFragment(MapFragment::class.java)
                    true
                }

                R.id.navigation_profile -> {
                    Log.d("NavBarFragment", "Profile button clicked")
                    //(activity as MainActivity).showFragment(ProfileFragment::class.java)
                    true
                }

                else -> false
            }
        }

        // Set the default selected item
//        navView.selectedItemId = R.id.navigation_search
    }
}