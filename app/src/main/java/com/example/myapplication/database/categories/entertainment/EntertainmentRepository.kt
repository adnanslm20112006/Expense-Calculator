package com.example.myapplication.database.categories.entertainment

import androidx.lifecycle.LiveData
import com.example.myapplication.database.categories.EntertainmentData

class EntertainmentRepository(private val entertainmentDao: EntertainmentDao) {
    suspend fun addEntertainment(entertainmentData: EntertainmentData) { entertainmentDao.addEntertainment(entertainmentData) }
    fun readEntertainmentData(month: Int, year: Int): LiveData<List<Int>> { return entertainmentDao.readEntertainmentData(month, year) }
}