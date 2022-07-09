package com.example.myapplication.database.categories.other

import androidx.lifecycle.LiveData
import com.example.myapplication.database.categories.OtherData

class OtherRepository(private val otherDao: OtherDao) {
    suspend fun addOther(otherData: OtherData) { otherDao.addOther(otherData) }
    fun readOtherData(month: Int, year: Int): LiveData<List<Int>> {return  otherDao.readOtherData(month, year)}
}