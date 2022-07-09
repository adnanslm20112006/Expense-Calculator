package com.example.myapplication.database.expenses

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpenseViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ExpenseRepository

    init {
        val expenseDao = ExpenseDatabase.getDatabase(application).expenseDao()
        repository = ExpenseRepository(expenseDao)
    }

    fun addExpense(expense: Expense) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addExpense(expense)
        }
    }

    fun readAllData(month: Int, year: Int): LiveData<List<Expense>> = repository.readAllData(month, year)
    fun readExpenseData(month: Int, year: Int): LiveData<List<Double>> = repository.readExpenseData(month, year)
    fun reaYearData(year: Int): LiveData<List<Double>> = repository.readYearData(year)
}