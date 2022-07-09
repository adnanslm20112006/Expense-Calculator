package com.example.myapplication.database.otherCategories

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface OtherCategoriesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCategory(category: OtherCategoriesData)

    @Query("SELECT * FROM other_categories_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<OtherCategoriesData>>
}