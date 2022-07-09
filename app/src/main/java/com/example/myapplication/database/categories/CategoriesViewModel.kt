package com.example.myapplication.database.categories

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.database.categories.emergency.EmergencyRepository
import com.example.myapplication.database.categories.entertainment.EntertainmentRepository
import com.example.myapplication.database.categories.family.FamilyRepository
import com.example.myapplication.database.categories.gifts.GiftsRepository
import com.example.myapplication.database.categories.health.HealthRepository
import com.example.myapplication.database.categories.other.OtherRepository
import com.example.myapplication.database.categories.restaurant.RestaurantRepository
import com.example.myapplication.database.categories.shopping.ShoppingRepository
import com.example.myapplication.database.categories.transport.TransportRepository
import com.example.myapplication.database.categories.travel.TravelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriesViewModel(application: Application): AndroidViewModel(application) {
    private val restaurantRepository: RestaurantRepository
    private val shoppingRepository: ShoppingRepository
    private val transportRepository: TransportRepository
    private val healthRepository: HealthRepository
    private val familyRepository: FamilyRepository
    private val giftsRepository: GiftsRepository
    private val entertainmentRepository: EntertainmentRepository
    private val emergencyRepository: EmergencyRepository
    private val travelRepository: TravelRepository
    private val otherRepository: OtherRepository
    init {
        val restaurantDao = RestaurantDatabase.getDatabase(application).restaurantDao()
        val shoppingDao = ShoppingDatabase.getDatabase(application).shoppingDao()
        val transportDao = TransportDatabase.getDatabase(application).transportDao()
        val healthDao = HealthDatabase.getDatabase(application).healthDao()
        val familyDao = FamilyDatabase.getDatabase(application).familyDao()
        val giftsDao = GiftsDatabase.getDatabase(application).giftsDao()
        val entertainmentDao = EntertainmentDatabase.getDatabase(application).entertainmentDao()
        val emergencyDao = EmergencyDatabase.getDatabase(application).emergencyDao()
        val travelDao = TravelDatabase.getDatabase(application).travelDao()
        val otherDao = OtherDatabase.getDatabase(application).otherDao()
        restaurantRepository = RestaurantRepository(restaurantDao)
        shoppingRepository = ShoppingRepository(shoppingDao)
        transportRepository = TransportRepository(transportDao)
        healthRepository = HealthRepository(healthDao)
        familyRepository = FamilyRepository(familyDao)
        giftsRepository = GiftsRepository(giftsDao)
        entertainmentRepository = EntertainmentRepository(entertainmentDao)
        emergencyRepository = EmergencyRepository(emergencyDao)
        travelRepository = TravelRepository(travelDao)
        otherRepository = OtherRepository(otherDao)
    }
    fun addRestaurant(restaurantData: RestaurantData) {
        viewModelScope.launch (Dispatchers.IO) {
            restaurantRepository.addRestaurant(restaurantData)
        }
    }
    fun addShopping(shoppingData: ShoppingData) {
        viewModelScope.launch (Dispatchers.IO) {
            shoppingRepository.addShopping(shoppingData)
        }
    }
    fun addTransport(transportData: TransportData) {
        viewModelScope.launch(Dispatchers.IO) {
            transportRepository.addTransport(transportData)
        }
    }
    fun addHealth(healthData: HealthData) {
        viewModelScope.launch(Dispatchers.IO) {
            healthRepository.addHealth(healthData)
        }
    }
    fun addFamily(familyData: FamilyData) {
        viewModelScope.launch(Dispatchers.IO) {
            familyRepository.addFamily(familyData)
        }
    }
    fun addGifts(giftsData: GiftsData) {
        viewModelScope.launch(Dispatchers.IO) {
            giftsRepository.addGifts(giftsData)
        }
    }
    fun addEntertainment(entertainmentData: EntertainmentData) {
        viewModelScope.launch(Dispatchers.IO) {
            entertainmentRepository.addEntertainment(entertainmentData)
        }
    }
    fun addEmergency(emergencyData: EmergencyData) {
        viewModelScope.launch(Dispatchers.IO) {
            emergencyRepository.addEmergency(emergencyData)
        }
    }
    fun addTravel(travelData: TravelData) {
        viewModelScope.launch(Dispatchers.IO) {
            travelRepository.addTravel(travelData)
        }
    }
    fun addOther(otherData: OtherData) {
        viewModelScope.launch(Dispatchers.IO) {
            otherRepository.addOther(otherData)
        }
    }
    fun readRestaurantData(month: Int, year: Int): LiveData<List<Int>> = restaurantRepository.readRestaurantData(month, year)
    fun readShoppingData(month: Int, year: Int): LiveData<List<Int>> = shoppingRepository.readShoppingData(month, year)
    fun readTransportData(month: Int, year: Int): LiveData<List<Int>> = transportRepository.readTransportData(month, year)
    fun readHealthData(month: Int, year: Int): LiveData<List<Int>> = healthRepository.readHealthData(month, year)
    fun readFamilyData(month: Int, year: Int): LiveData<List<Int>> = familyRepository.readFamilyData(month, year)
    fun readGiftsData(month: Int, year: Int): LiveData<List<Int>> = giftsRepository.readGiftsData(month, year)
    fun readEntertainmentData(month: Int, year: Int): LiveData<List<Int>> = entertainmentRepository.readEntertainmentData(month, year)
    fun readEmergencyData(month: Int, year: Int): LiveData<List<Int>> = emergencyRepository.readEmergencyData(month, year)
    fun readTravelData(month: Int, year: Int): LiveData<List<Int>> = travelRepository.readTravelData(month, year)
    fun readOtherData(month: Int, year: Int): LiveData<List<Int>> = otherRepository.readOtherData(month, year)
}