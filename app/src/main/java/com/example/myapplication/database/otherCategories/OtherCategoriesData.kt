package com.example.myapplication.database.otherCategories

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "other_categories_table")
data class OtherCategoriesData(
    @PrimaryKey(autoGenerate = true)
    val id: Int ,
    val imageId: Int,
    val text: String
    )