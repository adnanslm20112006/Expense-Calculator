package com.example.myapplication.database.categories.health

import androidx.lifecycle.LiveData
import com.example.myapplication.database.categories.HealthData

class HealthRepository(private val healthDao: HealthDao) {
    suspend fun addHealth(healthData: HealthData) { healthDao.addHealth(healthData) }
    fun readHealthData(month: Int, year: Int): LiveData<List<Int>> { return healthDao.readHealthData(month, year) }
}