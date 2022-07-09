package com.example.myapplication.fragments.viewPager.expense

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.database.expenses.ExpenseViewModel
import com.example.myapplication.databinding.FragmentExpensePercentageBinding
import java.util.*


class FirstScreen2 : Fragment() {
    private lateinit var binding: FragmentExpensePercentageBinding
    private lateinit var viewModel: ExpenseViewModel
    private lateinit var preferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentExpensePercentageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        preferences = requireActivity().getSharedPreferences("User", Context.MODE_PRIVATE)
        val c = Calendar.getInstance()
        val month = c[Calendar.MONTH] + 1
        val year = c[Calendar.YEAR]
        viewModel = ViewModelProvider(this)[ExpenseViewModel::class.java]
        viewModel.readExpenseData(month, year).observe(viewLifecycleOwner) {
            var value = 0.0
            for (i in it.indices) {
                value += it[i]
                if (value >= preferences.getInt("salary", 0)) {
                    val indicator = "100.0%"
                    binding.indicator.text = indicator
                    binding.progressBar.progress = 50
                }
                else {
                    binding.indicator.text = String.format("%.1f", value * 100 / (preferences.getInt("salary", 0).toDouble())).plus("%")
                    binding.progressBar.progress = kotlin.math.ceil((value * 100 / (preferences.getInt("salary", 0).toDouble())) / 2).toInt()
                }
            }
        }
        try {
            var value = 0.0
            for (i in viewModel.readExpenseData(month, year).value?.indices!!) {
                value += viewModel.readExpenseData(month, year).value!![i]
                if (value >= preferences.getInt("salary", 0)) {
                    val indicator = "100.0%"
                    binding.indicator.text = indicator
                    binding.progressBar.progress = 50
                }
                else {
                    binding.indicator.text = String.format("%.1f", (value * 100) / (preferences.getInt("salary", 0).toDouble())).plus("%")
                    binding.progressBar.progress = kotlin.math.ceil((value * 100 / (preferences.getInt("salary", 0).toDouble())) / 2).toInt()
                }
            }
        } catch (e: Exception) {
            binding.indicator.text = "0.0%"
            binding.progressBar.progress = 0
        }
    }
}