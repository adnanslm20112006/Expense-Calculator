package com.example.myapplication.database.categories

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurant_table") data class RestaurantData(@PrimaryKey(autoGenerate = true) val id: Int, val restaurant: Int, val month: Int, val year: Int)
@Entity(tableName = "shopping_table") data class ShoppingData(@PrimaryKey(autoGenerate = true)  val id: Int, val shopping: Int, val month: Int, val year: Int)
@Entity(tableName = "transport_table") data class TransportData(@PrimaryKey(autoGenerate = true)  val id: Int, val transport: Int, val month: Int, val year: Int)
@Entity(tableName = "health_table") data class HealthData(@PrimaryKey(autoGenerate = true)  val id: Int, val health: Int, val month: Int, val year: Int)
@Entity(tableName = "family_table") data class FamilyData(@PrimaryKey(autoGenerate = true)  val id: Int, val family: Int, val month: Int, val year: Int)
@Entity(tableName = "gifts_table") data class GiftsData(@PrimaryKey(autoGenerate = true)  val id: Int, val gifts: Int, val month: Int, val year: Int)
@Entity(tableName = "entertainment_table") data class EntertainmentData(@PrimaryKey(autoGenerate = true)  val id: Int, val entertainment: Int, val month: Int, val year: Int)
@Entity(tableName = "emergency_table") data class EmergencyData(@PrimaryKey(autoGenerate = true)  val id: Int, val emergency: Int, val month: Int, val year: Int)
@Entity(tableName = "travel_table") data class TravelData(@PrimaryKey(autoGenerate = true)  val id: Int, val travel: Int, val month: Int, val year: Int)
@Entity(tableName = "other_table") data class OtherData(@PrimaryKey(autoGenerate = true)  val id: Int, val other: Int, val month: Int, val year: Int)
