package com.example.myapplication.database.categories.travel

import androidx.lifecycle.LiveData
import com.example.myapplication.database.categories.TravelData

class TravelRepository(private val travelDao: TravelDao) {
    suspend fun addTravel(travelData: TravelData) { travelDao.addTravel(travelData) }
    fun readTravelData(month: Int, year: Int): LiveData<List<Int>> { return travelDao.readTravelData(month, year) }
}