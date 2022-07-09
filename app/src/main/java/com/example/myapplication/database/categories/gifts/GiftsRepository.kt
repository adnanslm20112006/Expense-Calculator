package com.example.myapplication.database.categories.gifts

import androidx.lifecycle.LiveData
import com.example.myapplication.database.categories.GiftsData

class GiftsRepository(private val giftsDao: GiftsDao) {
    suspend fun addGifts(giftsData: GiftsData) { giftsDao.addGift(giftsData) }
    fun readGiftsData(month: Int, year: Int): LiveData<List<Int>> { return giftsDao.readGiftsData(month, year) }
    }