package com.example.myapplication.network.interfaces

import com.example.myapplication.network.objects.RetrofitInstances
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrenciesApiService {
    @GET("convert")
    suspend fun getJson(
        @Query("q") from_to: String,
        @Query("compact") compact: String,
        @Query("apiKey") key: String
    ): String {
        return RetrofitInstances.retrofitService.getJson(from_to, compact, key)
    }
}