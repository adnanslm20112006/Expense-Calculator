package com.example.myapplication.database.expenses

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses_table")
data class Expense (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val expenseDate: String,
    val expenseType: String,
    val expenseCost: Double,
    val month: Int,
    val year: Int
)