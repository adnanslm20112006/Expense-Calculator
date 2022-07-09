package com.example.myapplication.database.categories.emergency

import androidx.lifecycle.LiveData
import com.example.myapplication.database.categories.EmergencyData

class EmergencyRepository(private val emergencyDao: EmergencyDao) {
    suspend fun addEmergency(emergencyData: EmergencyData) { emergencyDao.addEmergency(emergencyData) }
    fun readEmergencyData(month: Int, year: Int): LiveData<List<Int>> {return emergencyDao.readEmergencyData(month, year) }
}