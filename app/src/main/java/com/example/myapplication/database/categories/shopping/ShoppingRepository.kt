package com.example.myapplication.database.categories.shopping

import androidx.lifecycle.LiveData
import com.example.myapplication.database.categories.ShoppingData

class ShoppingRepository(private val shoppingDao: ShoppingDao) {
    suspend fun addShopping(shoppingData: ShoppingData) { shoppingDao.addShopping(shoppingData) }
    fun readShoppingData(month: Int, year: Int): LiveData<List<Int>> { return shoppingDao.readShoppingData(month, year) }
}