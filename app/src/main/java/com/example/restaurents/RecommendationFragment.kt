package com.example.restaurents

import android.os.Bundle
import android.os.Handler
import android.os.Looper

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.restaurents.databinding.FragmentRecommendationBinding


class RecommendationFragment : Fragment() {

    private var _binding: FragmentRecommendationBinding? = null
    private val binding get() = _binding!!
    private lateinit var restaurantAdapter: RestaurantAdapter
    private val restaurants: MutableList<Restaurant> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restaurants.add(Restaurant("Restaurant 1", "Russian", "$$$"))
        restaurants.add(Restaurant("Restaurant 2", "Italian", "$$"))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecommendationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
        restaurantAdapter = RestaurantAdapter(restaurants)
        binding.recyclerView.adapter = restaurantAdapter

        Handler(Looper.getMainLooper()).post {
            childFragmentManager.beginTransaction()
                .replace(binding.navBarContainer.id, NavBarFragment())
                .commitNow()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
