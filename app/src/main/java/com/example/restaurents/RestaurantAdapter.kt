package com.example.restaurents

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurants.R

class RestaurantAdapter(private val restaurants: List<Restaurant>) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {


    class RestaurantViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        private val typeTextView: TextView = view.findViewById(R.id.typeTextView)
        private val priceCategoryTextView: TextView = view.findViewById(R.id.priceCategoryTextView)
        private val addressTextView: TextView = view.findViewById(R.id.addressTextView)

        fun bind(restaurant: Restaurant) {
            titleTextView.text = restaurant.name
            typeTextView.text = restaurant.type
            priceCategoryTextView.text = restaurant.priceCategory
            addressTextView.text = restaurant.address
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_restaurant, parent, false)

        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bind(restaurants[position])
    }

    override fun getItemCount(): Int {
        return restaurants.size
    }
}