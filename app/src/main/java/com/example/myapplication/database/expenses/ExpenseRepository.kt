package com.example.myapplication.database.expenses

import androidx.lifecycle.LiveData

class ExpenseRepository(private val ExpenseDao: ExpenseDao) {

    fun readAllData(month: Int, year: Int): LiveData<List<Expense>> = ExpenseDao.readAllData(month, year)
    fun readExpenseData(month: Int, year: Int): LiveData<List<Double>> = ExpenseDao.readExpenseData(month, year)
    fun readYearData(year: Int): LiveData<List<Double>> = ExpenseDao.readYearData(year)
    suspend fun addExpense(expense: Expense) {
        ExpenseDao.addExpense(expense)
    }
}