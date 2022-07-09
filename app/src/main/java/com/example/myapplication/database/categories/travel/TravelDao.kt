package com.example.myapplication.database.categories.travel

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.database.categories.TravelData

@Dao
interface TravelDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) suspend fun addTravel(travelData: TravelData)
    @Query("SELECT travel FROM travel_table WHERE month=:month AND year=:year ORDER BY id DESC") fun readTravelData(month: Int, year: Int): LiveData<List<Int>>
}