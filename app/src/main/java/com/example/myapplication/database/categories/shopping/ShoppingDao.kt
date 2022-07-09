package com.example.myapplication.database.categories.shopping

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.database.categories.ShoppingData

@Dao
interface ShoppingDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) suspend fun addShopping(shoppingData: ShoppingData)
    @Query("SELECT shopping FROM shopping_table WHERE month=:month AND year=:year ORDER BY id DESC") fun readShoppingData(month: Int, year: Int): LiveData<List<Int>>
}