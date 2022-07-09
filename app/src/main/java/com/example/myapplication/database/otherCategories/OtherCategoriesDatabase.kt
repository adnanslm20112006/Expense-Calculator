package com.example.myapplication.database.otherCategories

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [OtherCategoriesData::class], version = 1, exportSchema = false)
abstract class OtherCategoriesDatabase : RoomDatabase() {

    abstract fun otherCategoriesDao(): OtherCategoriesDao

    companion object {
        @Volatile
        private var INSTANCE: OtherCategoriesDatabase? = null
        fun getDatabase(context: Context): OtherCategoriesDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OtherCategoriesDatabase::class.java,
                    "other_categories_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}