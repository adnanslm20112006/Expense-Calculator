package com.example.myapplication.database.otherCategories

import androidx.lifecycle.LiveData

class OtherCategoriesRepository(private val otherCategoriesDao: OtherCategoriesDao) {
    val readAllData: LiveData<List<OtherCategoriesData>> = otherCategoriesDao.readAllData()
    suspend fun addCategory(category: OtherCategoriesData) {
        otherCategoriesDao.addCategory(category)
    }
}