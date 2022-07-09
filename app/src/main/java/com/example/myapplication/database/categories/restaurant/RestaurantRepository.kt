package com.example.myapplication.database.categories.restaurant

import androidx.lifecycle.LiveData
import com.example.myapplication.database.categories.RestaurantData

class RestaurantRepository(private val restaurantDao: RestaurantDao) {
    suspend fun addRestaurant(restaurantData: RestaurantData) { restaurantDao.addRestaurant(restaurantData) }
    fun readRestaurantData(month: Int, year: Int): LiveData<List<Int>> { return restaurantDao.readRestaurantData(month, year) }
}