package com.example.restaurents.models

import java.time.OffsetDateTime

data class FavoriteRestaurant(
    val id: Int,
    val name: String,
    val address: String,
    val phoneNumber: String,
    val openingHours: OffsetDateTime,
    val description: String,
    val userId: Int,
    val menuCategoryId: Int,
    val priceCategoryId: Int
)
