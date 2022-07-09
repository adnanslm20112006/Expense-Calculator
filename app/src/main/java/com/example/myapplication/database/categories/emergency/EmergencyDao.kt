package com.example.myapplication.database.categories.emergency

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.database.categories.EmergencyData

@Dao
interface EmergencyDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) suspend fun addEmergency(emergencyData: EmergencyData)
    @Query("SELECT emergency FROM emergency_table WHERE month=:month AND year=:year ORDER BY id DESC") fun readEmergencyData(month: Int, year: Int): LiveData<List<Int>>
}