package com.example.myapplication.database.categories.health

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.database.categories.HealthData

@Dao
interface HealthDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) suspend fun addHealth(healthData: HealthData)
    @Query("SELECT health FROM health_table WHERE month=:month AND year=:year ORDER BY id DESC") fun readHealthData(month: Int, year: Int): LiveData<List<Int>>
}