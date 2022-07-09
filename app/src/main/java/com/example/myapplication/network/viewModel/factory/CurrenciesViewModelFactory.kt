package com.example.myapplication.network.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.network.viewModel.CurrenciesViewModel

@Suppress("UNCHECKED_CAST")
class CurrenciesViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CurrenciesViewModel() as T
    }
}