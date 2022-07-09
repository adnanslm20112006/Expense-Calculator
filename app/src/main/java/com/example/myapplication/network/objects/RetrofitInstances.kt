package com.example.myapplication.network.objects

import com.example.myapplication.network.interfaces.CurrenciesApiService
import com.example.myapplication.network.objects.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitInstances {
    private val scalar = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val retrofitService : CurrenciesApiService by lazy { scalar.create(CurrenciesApiService::class.java) }

}