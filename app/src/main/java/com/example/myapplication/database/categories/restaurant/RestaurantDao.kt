package com.example.myapplication.database.categories.restaurant

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.database.categories.RestaurantData

@Dao
interface RestaurantDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) suspend fun addRestaurant(restaurantData: RestaurantData)
    @Query("SELECT restaurant FROM restaurant_table WHERE month=:month AND year=:year ORDER BY id DESC") fun readRestaurantData(month: Int, year: Int): LiveData<List<Int>>
}