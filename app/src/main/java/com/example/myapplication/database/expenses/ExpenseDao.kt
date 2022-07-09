package com.example.myapplication.database.expenses

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ExpenseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addExpense(expense: Expense)
    @Query("SELECT * FROM expenses_table WHERE month=:month AND year=:year ORDER BY id ASC")
    fun readAllData(month: Int, year: Int): LiveData<List<Expense>>
    @Query("SELECT expenseCost FROM expenses_table WHERE month=:month AND year=:year ORDER BY id DESC")
    fun readExpenseData(month: Int, year: Int): LiveData<List<Double>>
    @Query("SELECT expenseCost FROM expenses_table WHERE year=:year")
    fun readYearData(year: Int): LiveData<List<Double>>
}