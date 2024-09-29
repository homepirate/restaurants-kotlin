package com.example.restaurents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
                    // Обработка нажатия на "Поиск"
                    true
                }
                R.id.navigation_recommendations -> {
                    // Обработка нажатия на "Рекомендации"
                    true
                }
                R.id.navigation_map -> {
                    // Обработка нажатия на "Карта"
                    true
                }
                R.id.navigation_profile -> {
                    // Обработка нажатия на "Профиль"
                    true
                }
                else -> false
            }
        }
    }
}
