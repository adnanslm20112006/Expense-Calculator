package com.example.myapplication.database.categories.transport

import androidx.lifecycle.LiveData
import com.example.myapplication.database.categories.TransportData

class TransportRepository(private val transportDao: TransportDao) {
    suspend fun addTransport(transportData: TransportData) { transportDao.addTransport(transportData) }
    fun readTransportData(month: Int, year: Int): LiveData<List<Int>> {return  transportDao.readTransportData(month, year)}
}