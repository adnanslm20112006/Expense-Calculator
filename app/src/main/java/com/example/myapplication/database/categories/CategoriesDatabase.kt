package com.example.myapplication.database.categories

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.database.categories.emergency.EmergencyDao
import com.example.myapplication.database.categories.entertainment.EntertainmentDao
import com.example.myapplication.database.categories.family.FamilyDao
import com.example.myapplication.database.categories.gifts.GiftsDao
import com.example.myapplication.database.categories.health.HealthDao
import com.example.myapplication.database.categories.other.OtherDao
import com.example.myapplication.database.categories.restaurant.RestaurantDao
import com.example.myapplication.database.categories.shopping.ShoppingDao
import com.example.myapplication.database.categories.transport.TransportDao
import com.example.myapplication.database.categories.travel.TravelDao

@Database(entities = [RestaurantData::class], version = 1, exportSchema = false )
abstract class RestaurantDatabase: RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao

    companion object {
        @Volatile
        private var INSTANCE: RestaurantDatabase? = null
        fun getDatabase(context: Context): RestaurantDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RestaurantDatabase::class.java,
                    "restaurant_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
@Database(entities = [ShoppingData::class], version = 1, exportSchema = false )
abstract class ShoppingDatabase: RoomDatabase() {

    abstract fun shoppingDao(): ShoppingDao

    companion object {
        @Volatile
        private var INSTANCE: ShoppingDatabase? = null
        fun getDatabase(context: Context): ShoppingDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShoppingDatabase::class.java,
                    "shopping_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
@Database(entities = [TransportData::class], version = 1, exportSchema = false )
abstract class TransportDatabase: RoomDatabase() {

    abstract fun transportDao(): TransportDao

    companion object {
        @Volatile
        private var INSTANCE: TransportDatabase? = null
        fun getDatabase(context: Context): TransportDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TransportDatabase::class.java,
                    "transport_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
@Database(entities = [HealthData::class], version = 1, exportSchema = false )
abstract class HealthDatabase: RoomDatabase() {

    abstract fun healthDao(): HealthDao

    companion object {
        @Volatile
        private var INSTANCE: HealthDatabase? = null
        fun getDatabase(context: Context): HealthDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HealthDatabase::class.java,
                    "health_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
@Database(entities = [FamilyData::class], version = 1, exportSchema = false )
abstract class FamilyDatabase: RoomDatabase() {

    abstract fun familyDao(): FamilyDao

    companion object {
        @Volatile
        private var INSTANCE: FamilyDatabase? = null
        fun getDatabase(context: Context): FamilyDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FamilyDatabase::class.java,
                    "family_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
@Database(entities = [GiftsData::class], version = 1, exportSchema = false )
abstract class GiftsDatabase: RoomDatabase() {

    abstract fun giftsDao(): GiftsDao

    companion object {
        @Volatile
        private var INSTANCE: GiftsDatabase? = null
        fun getDatabase(context: Context): GiftsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GiftsDatabase::class.java,
                    "gifts_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
@Database(entities = [EntertainmentData::class], version = 1, exportSchema = false )
abstract class EntertainmentDatabase: RoomDatabase() {

    abstract fun entertainmentDao(): EntertainmentDao

    companion object {
        @Volatile
        private var INSTANCE: EntertainmentDatabase? = null
        fun getDatabase(context: Context): EntertainmentDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EntertainmentDatabase::class.java,
                    "entertainment_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
@Database(entities = [EmergencyData::class], version = 1, exportSchema = false )
abstract class EmergencyDatabase: RoomDatabase() {

    abstract fun emergencyDao(): EmergencyDao

    companion object {
        @Volatile
        private var INSTANCE: EmergencyDatabase? = null
        fun getDatabase(context: Context): EmergencyDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EmergencyDatabase::class.java,
                    "emergency_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
@Database(entities = [TravelData::class], version = 1, exportSchema = false )
abstract class TravelDatabase: RoomDatabase() {

    abstract fun travelDao(): TravelDao

    companion object {
        @Volatile
        private var INSTANCE: TravelDatabase? = null
        fun getDatabase(context: Context): TravelDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TravelDatabase::class.java,
                    "travel_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
    @Database(entities = [OtherData::class], version = 1, exportSchema = false )
    abstract class OtherDatabase: RoomDatabase() {

        abstract fun otherDao(): OtherDao

        companion object {
            @Volatile
            private var INSTANCE: OtherDatabase? = null
            fun getDatabase(context: Context): OtherDatabase {
                val tempInstance = INSTANCE
                if (tempInstance != null) {
                    return tempInstance
                }
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        OtherDatabase::class.java,
                        "other_table"
                    ).build()
                    INSTANCE = instance
                    return instance
                }
            }
        }
    }