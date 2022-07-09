package com.example.myapplication.database.otherCategories

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OtherCategoriesViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<OtherCategoriesData>>
    private val repository: OtherCategoriesRepository

    init {
        val categoriesDao = OtherCategoriesDatabase.getDatabase(application).otherCategoriesDao()
        repository = OtherCategoriesRepository(categoriesDao)
        readAllData = repository.readAllData
    }

    fun addCategory(category: OtherCategoriesData) {
        viewModelScope.launch (Dispatchers.IO) {
            repository.addCategory(category)
        }
    }
}