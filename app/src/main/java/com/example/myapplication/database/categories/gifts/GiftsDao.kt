package com.example.myapplication.database.categories.gifts

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.database.categories.GiftsData

@Dao
interface GiftsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) suspend fun addGift(giftsData: GiftsData)
    @Query("SELECT gifts FROM gifts_table WHERE month=:month AND year=:year ORDER BY id DESC") fun readGiftsData(month: Int, year: Int): LiveData<List<Int>>
}