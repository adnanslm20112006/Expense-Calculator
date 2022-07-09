package com.example.myapplication.database.categories.family

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.database.categories.FamilyData

@Dao
interface FamilyDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) suspend fun addFamily(familyData: FamilyData)
    @Query("SELECT family FROM family_table WHERE month=:month AND year=:year ORDER BY id DESC") fun readFamilyData(month: Int, year: Int): LiveData<List<Int>>
}