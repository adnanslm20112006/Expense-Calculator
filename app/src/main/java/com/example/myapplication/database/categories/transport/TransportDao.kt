package com.example.myapplication.database.categories.transport

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.database.categories.TransportData

@Dao
interface TransportDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) suspend fun addTransport(transportData: TransportData)
    @Query("SELECT transport FROM transport_table WHERE month=:month AND year=:year ORDER BY id DESC") fun readTransportData(month: Int, year: Int): LiveData<List<Int>>
}