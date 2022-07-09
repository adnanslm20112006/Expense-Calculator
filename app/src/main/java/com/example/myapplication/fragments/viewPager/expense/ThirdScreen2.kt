package com.example.myapplication.fragments.viewPager.expense

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.database.expenses.ExpenseViewModel
import com.example.myapplication.databinding.CateogriesDetailsBinding
import java.util.*

class ThirdScreen2 : Fragment() {
    private lateinit var binding: CateogriesDetailsBinding
    private lateinit var preferences: SharedPreferences
    private lateinit var viewModel: ExpenseViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CateogriesDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val c = Calendar.getInstance()
        val month = c[Calendar.MONTH] + 1
        val year = c[Calendar.YEAR]
        viewModel = ViewModelProvider(this)[ExpenseViewModel::class.java]
        preferences = requireActivity().getSharedPreferences("User", Context.MODE_PRIVATE)
        viewModel.readExpenseData(month, year).observe(viewLifecycleOwner) {
            var value = 0.0
            for (i in it.indices) {
                value += it[i]
                if (value >= preferences.getInt("salary", 0)) {
                    val indicatorValue = "0.0%"
                    binding.totalPaidValue.text = value.toString()
                    binding.indicatorValue.text = indicatorValue
                    binding.totalIndicator.progress = 0
                }
                else {
                    binding.totalPaidValue.text = value.toString()
                    binding.indicatorValue.text = String.format("%.1f",100 - (value * 100.0 / preferences.getInt("salary", 0))).plus("%")
                    binding.totalIndicator.progress = (kotlin.math.ceil(100 - (value * 100.0 / preferences.getInt("salary", 0))) / 2).toInt()
                }
            }
        }
        try {
            var value = 0.0
            for (i in viewModel.readExpenseData(month, year).value?.indices!!) {
                value += viewModel.readExpenseData(month, year).value!![i]
                if (value >= preferences.getInt("salary", 0)) {
                    val indicatorValue = "100.0%"
                    binding.totalPaidValue.text = value.toString()
                    binding.indicatorValue.text = indicatorValue
                    binding.totalIndicator.progress = 50
                }
                else {
                    binding.totalPaidValue.text = value.toString()
                    binding.indicatorValue.text = String.format("%.1f",100 - (value * 100.0 / preferences.getInt("salary", 0))).plus("%")
                    binding.totalIndicator.progress = (kotlin.math.ceil(100 - (value * 100.0 / preferences.getInt("salary", 0))) / 2).toInt()
                }
            }
        }
        catch (e: Exception) {
            binding.totalPaidValue.text = "0"
            val indicatorValue = "100.0%"
            binding.indicatorValue.text = indicatorValue
            binding.totalIndicator.progress = 50
        }
    }
}