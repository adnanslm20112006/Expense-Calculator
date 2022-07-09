package com.example.myapplication.database.categories.other

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.database.categories.OtherData

@Dao
interface OtherDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) suspend fun addOther(otherData: OtherData)
    @Query("SELECT other FROM other_table WHERE month=:month AND year=:year ORDER BY id DESC") fun readOtherData(month: Int, year: Int): LiveData<List<Int>>
}