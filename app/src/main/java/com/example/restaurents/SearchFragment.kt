package com.example.restaurents

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurants.R
import com.example.restaurants.databinding.FragmentSearchBinding
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import kotlin.random.Random


class SearchFragment : Fragment() {

    // Using view binding to avoid null pointer exceptions
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.root.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Загружаем данные из CSV
        val restaurants = loadRestaurantsFromCSV(requireContext())

        val adapter = RestaurantAdapter(restaurants)
        recyclerView.adapter = adapter

        // Заменяет фрагмент поиска
        childFragmentManager.beginTransaction()
            .replace(binding.searchFieldContainer.id, SearchFieldFragment())
            .commitNow()

        childFragmentManager.beginTransaction()
            .replace(binding.searchFieldContainer.id, SearchFieldFragment()) // Ensure this matches your layout ID
            .commitNow()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Clear binding reference to prevent memory leaks
    }



    fun loadRestaurantsFromCSV(context: Context): List<Restaurant> {
        val restaurants = mutableListOf<Restaurant>()

        // Получаем доступ к ресурсу
        val inputStream = context.resources.openRawResource(R.raw.rests) // Замените на имя вашего файла без расширения
        val reader = BufferedReader(InputStreamReader(inputStream))
        val priceCategories = arrayOf("$", "$$", "$$$")

        reader.use { br ->
            br.forEachLine { line ->
                val columns = line.split(";") // Предполагается, что колонки разделены запятыми
                if (columns.size > 8) { // Убедитесь, что количество столбцов верно
                    val type = columns[5]
                    if (type in listOf("кафе", "бар", "ресторан")) {
                        val restaurant = Restaurant(
                            name = columns[1],
                            type = type,
                            priceCategory = priceCategories[Random.nextInt(priceCategories.size)],
                            address = columns[8]
                        )
                        restaurants.add(restaurant)
                    }
                }
            }
        }

        return restaurants
    }
}
