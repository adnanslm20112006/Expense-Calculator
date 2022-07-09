package com.example.myapplication.network.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.GlobalApplication.getAppContext
import com.example.myapplication.network.objects.RetrofitInstances
import kotlinx.coroutines.launch

class CurrenciesViewModel() : ViewModel() {

    val code: MutableLiveData<Boolean> = MutableLiveData()
     val status = MutableLiveData<String>()
    init {
        getJson()
    }
    fun getJson() {
        val preferences = getAppContext().getSharedPreferences("User", Context.MODE_PRIVATE)
        viewModelScope.launch {
            try {
                val listResult = RetrofitInstances.retrofitService.getJson("${preferences.getString("from", "TRY").toString()}_${preferences.getString("to", "USD").toString()}", "ultra", "e0ba65b5ae1bf1b0019c")
                status.value = listResult
                code.value = true
            } catch (e: Exception) {
                code.value = false
            }
        }
    }

}