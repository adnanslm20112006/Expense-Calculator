package com.example.myapplication.database.categories.family

import androidx.lifecycle.LiveData
import com.example.myapplication.database.categories.FamilyData

class FamilyRepository(private val familyDao: FamilyDao) {
    suspend fun addFamily(familyData: FamilyData) { familyDao.addFamily(familyData) }
    fun readFamilyData(month: Int, year: Int): LiveData<List<Int>> { return familyDao.readFamilyData(month, year) }
}