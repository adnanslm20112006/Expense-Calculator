package com.example.myapplication.database.categories.entertainment

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.database.categories.EntertainmentData

@Dao
interface EntertainmentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) suspend fun addEntertainment(entertainmentData: EntertainmentData)
    @Query("SELECT entertainment FROM entertainment_table WHERE month=:month AND year=:year ORDER BY id DESC") fun readEntertainmentData(month: Int, year: Int): LiveData<List<Int>>
}