package com.example.myapplication.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.database.categories.CategoriesViewModel
import com.example.myapplication.database.expenses.ExpenseViewModel
import com.example.myapplication.databinding.FragmentInformationBinding
import java.util.*
import kotlin.math.roundToInt

class Information : Fragment() {
    private lateinit var binding: FragmentInformationBinding
    private lateinit var preferences: SharedPreferences
    private val expenseViewModel: ExpenseViewModel by viewModels()
    private val args by navArgs<InformationArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val c: Calendar = Calendar.getInstance()
        val month = c[Calendar.MONTH] + 1
        val year = c[Calendar.YEAR]
        preferences = requireActivity().getSharedPreferences("User", Context.MODE_PRIVATE)
        binding.categoryName.text = args.category
        val viewModel = ViewModelProvider(this)[CategoriesViewModel::class.java]
        when (binding.categoryName.text) {
            getString(R.string.restaurant) -> {
                try {
                    var value = 0
                    for (i in viewModel.readRestaurantData(month, year).value?.indices!!) {
                        value += viewModel.readRestaurantData(month, year).value!![i]
                        binding.indicatorText.text =
                            String.format("%.1f", value * 100.0 / preferences.getInt("salary", 0))
                    }
                } catch (e: Exception) {
                    binding.indicatorText.text = "0.0%"
                    binding.paymentIndicator.progress = 0
                }
                viewModel.readRestaurantData(month, year).observe(viewLifecycleOwner) { it ->
                    var value = 0
                    for (i in it.indices) {
                        value += it[i]
                        binding.paymentIndicator.progress =
                            if (kotlin.math.ceil((value * 100.0 / preferences.getInt("salary", 0)))
                                    .roundToInt() > preferences.getInt("salary", 0)
                            ) 100
                            else kotlin.math.ceil((value * 100.0 / preferences.getInt("salary", 0)))
                                .roundToInt()
                        binding.indicatorText.text =
                            if ((value * 100.0 / preferences.getInt("salary", 0)
                                    .toDouble()) > 100.0
                            ) "100.0%"
                            else String.format(
                                "%.1f",
                                (value * 100.0 / preferences.getInt("salary", 0).toDouble())
                            ).plus("%")
                        expenseViewModel.readExpenseData(month, year).observe(viewLifecycleOwner) {
                            var doubleValue = 0.0
                            for (j in it) {
                                doubleValue += j
                            }
                            binding.totalPerMonthNumber.text =
                                String.format("%.1f", value * 100.0 / doubleValue)
                                    .plus("%")
                        }
                    }
                    binding.totalNumber.text = value.toString()
                }
            }
            getString(R.string.shopping) -> {
                try {
                    var value = 0
                    for (i in viewModel.readShoppingData(month, year).value?.indices!!) {
                        value += viewModel.readShoppingData(month, year).value!![i]
                        binding.indicatorText.text =
                            String.format("%.1f", value * 100.0 / preferences.getInt("salary", 0))
                    }
                } catch (e: Exception) {
                    binding.indicatorText.text = "0.0%"
                    binding.paymentIndicator.progress = 0
                }
                viewModel.readShoppingData(month, year).observe(viewLifecycleOwner) { it ->
                    var value = 0
                    for (i in it.indices) {
                        value += it[i]
                        binding.paymentIndicator.progress =
                            if (kotlin.math.ceil((value * 100.0 / preferences.getInt("salary", 0)))
                                    .roundToInt() > preferences.getInt("salary", 0)
                            ) 100
                            else kotlin.math.ceil((value * 100.0 / preferences.getInt("salary", 0)))
                                .roundToInt()
                        binding.indicatorText.text =
                            if ((value * 100.0 / preferences.getInt("salary", 0)
                                    .toDouble()) > 100.0
                            ) "100.0%"
                            else String.format(
                                "%.1f",
                                (value * 100.0 / preferences.getInt("salary", 0).toDouble())
                            ).plus("%")
                        expenseViewModel.readExpenseData(month, year).observe(viewLifecycleOwner) {
                            var doubleValue = 0.0
                            for (j in it) {
                                doubleValue += j
                            }
                            binding.totalPerMonthNumber.text =
                                String.format("%.1f", value * 100.0 / doubleValue)
                                    .plus("%")
                        }
                    }
                    binding.totalNumber.text = value.toString()
                }
            }
            getString(R.string.transport) -> {
                try {
                    var value = 0
                    for (i in viewModel.readTransportData(month, year).value?.indices!!) {
                        value += viewModel.readTransportData(month, year).value!![i]
                        binding.indicatorText.text =
                            String.format("%.1f", value * 100.0 / preferences.getInt("salary", 0))
                    }
                } catch (e: Exception) {
                    binding.indicatorText.text = "0.0%"
                    binding.paymentIndicator.progress = 0
                }
                viewModel.readTransportData(month, year).observe(viewLifecycleOwner) { it ->
                    var value = 0
                    for (i in it.indices) {
                        value += it[i]
                        binding.paymentIndicator.progress =
                            if (kotlin.math.ceil((value * 100.0 / preferences.getInt("salary", 0)))
                                    .roundToInt() > preferences.getInt("salary", 0)
                            ) 100
                            else kotlin.math.ceil((value * 100.0 / preferences.getInt("salary", 0)))
                                .roundToInt()
                        binding.indicatorText.text =
                            if ((value * 100.0 / preferences.getInt("salary", 0)
                                    .toDouble()) > 100.0
                            ) "100.0%"
                            else String.format(
                                "%.1f",
                                (value * 100.0 / preferences.getInt("salary", 0).toDouble())
                            ).plus("%")
                        expenseViewModel.readExpenseData(month, year).observe(viewLifecycleOwner) {
                            var doubleValue = 0.0
                            for (j in it) {
                                doubleValue += j
                            }
                            binding.totalPerMonthNumber.text =
                                String.format("%.1f", value * 100.0 / doubleValue)
                                    .plus("%")
                        }
                    }
                    binding.totalNumber.text = value.toString()
                }
            }
            getString(R.string.health) -> {
                try {
                    var value = 0
                    for (i in viewModel.readHealthData(month, year).value?.indices!!) {
                        value += viewModel.readHealthData(month, year).value!![i]
                        binding.indicatorText.text =
                            String.format("%.1f", value * 100.0 / preferences.getInt("salary", 0))
                    }
                } catch (e: Exception) {
                    binding.indicatorText.text = "0.0%"
                    binding.paymentIndicator.progress = 0
                }
                viewModel.readHealthData(month, year).observe(viewLifecycleOwner) { it ->
                    var value = 0
                    for (i in it.indices) {
                        value += it[i]
                        binding.paymentIndicator.progress =
                            if (kotlin.math.ceil((value * 100.0 / preferences.getInt("salary", 0)))
                                    .roundToInt() > preferences.getInt("salary", 0)
                            ) 100
                            else kotlin.math.ceil((value * 100.0 / preferences.getInt("salary", 0)))
                                .roundToInt()
                        binding.indicatorText.text =
                            if ((value * 100.0 / preferences.getInt("salary", 0)
                                    .toDouble()) > 100.0
                            ) "100.0%"
                            else String.format(
                                "%.1f",
                                (value * 100.0 / preferences.getInt("salary", 0).toDouble())
                            ).plus("%")
                        expenseViewModel.readExpenseData(month, year).observe(viewLifecycleOwner) {
                            var doubleValue = 0.0
                            for (j in it) {
                                doubleValue += j
                            }
                            binding.totalPerMonthNumber.text =
                                String.format("%.1f", value * 100.0 / doubleValue)
                                    .plus("%")
                        }
                    }
                    binding.totalNumber.text = value.toString()
                }
            }
            getString(R.string.family) -> {
                try {
                    var value = 0
                    for (i in viewModel.readFamilyData(month, year).value?.indices!!) {
                        value += viewModel.readFamilyData(month, year).value!![i]
                        binding.indicatorText.text =
                            String.format("%.1f", value * 100.0 / preferences.getInt("salary", 0))
                    }
                } catch (e: Exception) {
                    binding.indicatorText.text = "0.0%"
                    binding.paymentIndicator.progress = 0
                }
                viewModel.readFamilyData(month, year).observe(viewLifecycleOwner) { it ->
                    var value = 0
                    for (i in it.indices) {
                        value += it[i]
                        binding.paymentIndicator.progress =
                            if (kotlin.math.ceil((value * 100.0 / preferences.getInt("salary", 0)))
                                    .roundToInt() > preferences.getInt("salary", 0)
                            ) 100
                            else kotlin.math.ceil((value * 100.0 / preferences.getInt("salary", 0)))
                                .roundToInt()
                        binding.indicatorText.text =
                            if ((value * 100.0 / preferences.getInt("salary", 0)
                                    .toDouble()) > 100.0
                            ) "100.0%"
                            else String.format(
                                "%.1f",
                                (value * 100.0 / preferences.getInt("salary", 0).toDouble())
                            ).plus("%")
                        expenseViewModel.readExpenseData(month, year).observe(viewLifecycleOwner) {
                            var doubleValue = 0.0
                            for (j in it) {
                                doubleValue += j
                            }
                            binding.totalPerMonthNumber.text =
                                String.format("%.1f", value * 100.0 / doubleValue)
                                    .plus("%")
                        }
                    }
                    binding.totalNumber.text = value.toString()
                }
            }
            getString(R.string.gifts) -> {
                try {
                    var value = 0
                    for (i in viewModel.readGiftsData(month, year).value?.indices!!) {
                        value += viewModel.readGiftsData(month, year).value!![i]
                        binding.indicatorText.text =
                            String.format("%.1f", value * 100.0 / preferences.getInt("salary", 0))
                    }
                } catch (e: Exception) {
                    binding.indicatorText.text = "0.0%"
                    binding.paymentIndicator.progress = 0
                }
                viewModel.readGiftsData(month, year).observe(viewLifecycleOwner) { it ->
                    var value = 0
                    for (i in it.indices) {
                        value += it[i]
                        binding.paymentIndicator.progress =
                            if (kotlin.math.ceil((value * 100.0 / preferences.getInt("salary", 0)))
                                    .roundToInt() > preferences.getInt("salary", 0)
                            ) 100
                            else kotlin.math.ceil((value * 100.0 / preferences.getInt("salary", 0)))
                                .roundToInt()
                        binding.indicatorText.text =
                            if ((value * 100.0 / preferences.getInt("salary", 0)
                                    .toDouble()) > 100.0
                            ) "100.0%"
                            else String.format(
                                "%.1f",
                                (value * 100.0 / preferences.getInt("salary", 0).toDouble())
                            ).plus("%")
                        expenseViewModel.readExpenseData(month, year).observe(viewLifecycleOwner) {
                            var doubleValue = 0.0
                            for (j in it) {
                                doubleValue += j
                            }
                            binding.totalPerMonthNumber.text =
                                String.format("%.1f", value * 100.0 / doubleValue)
                                    .plus("%")
                        }
                    }
                    binding.totalNumber.text = value.toString()
                }
            }
            getString(R.string.entertainment) -> {
                try {
                    var value = 0
                    for (i in viewModel.readEntertainmentData(month, year).value?.indices!!) {
                        value += viewModel.readEntertainmentData(month, year).value!![i]
                        binding.indicatorText.text =
                            String.format("%.1f", value * 100.0 / preferences.getInt("salary", 0))
                    }
                } catch (e: Exception) {
                    binding.indicatorText.text = "0.0%"
                    binding.paymentIndicator.progress = 0
                }
                viewModel.readEntertainmentData(month, year).observe(viewLifecycleOwner) { it ->
                    var value = 0
                    for (i in it.indices) {
                        value += it[i]
                        binding.paymentIndicator.progress =
                            if (kotlin.math.ceil((value * 100.0 / preferences.getInt("salary", 0)))
                                    .roundToInt() > preferences.getInt("salary", 0)
                            ) 100
                            else kotlin.math.ceil((value * 100.0 / preferences.getInt("salary", 0)))
                                .roundToInt()
                        binding.indicatorText.text =
                            if ((value * 100.0 / preferences.getInt("salary", 0)
                                    .toDouble()) > 100.0
                            ) "100.0%"
                            else String.format(
                                "%.1f",
                                (value * 100.0 / preferences.getInt("salary", 0).toDouble())
                            ).plus("%")
                        expenseViewModel.readExpenseData(month, year).observe(viewLifecycleOwner) {
                            var doubleValue = 0.0
                            for (j in it) {
                                doubleValue += j
                            }
                            binding.totalPerMonthNumber.text =
                                String.format("%.1f", value * 100.0 / doubleValue)
                                    .plus("%")
                        }
                    }
                    binding.totalNumber.text = value.toString()
                }
            }
            getString(R.string.emergency) -> {
                try {
                    var value = 0
                    for (i in viewModel.readEmergencyData(month, year).value?.indices!!) {
                        value += viewModel.readEmergencyData(month, year).value!![i]
                        binding.indicatorText.text =
                            String.format("%.1f", value * 100.0 / preferences.getInt("salary", 0))
                    }
                } catch (e: Exception) {
                    binding.indicatorText.text = "0.0%"
                    binding.paymentIndicator.progress = 0
                }
                viewModel.readEmergencyData(month, year).observe(viewLifecycleOwner) { it ->
                    var value = 0
                    for (i in it.indices) {
                        value += it[i]
                        binding.paymentIndicator.progress =
                            if (kotlin.math.ceil((value * 100.0 / preferences.getInt("salary", 0)))
                                    .roundToInt() > preferences.getInt("salary", 0)
                            ) 100
                            else kotlin.math.ceil((value * 100.0 / preferences.getInt("salary", 0)))
                                .roundToInt()
                        binding.indicatorText.text =
                            if ((value * 100.0 / preferences.getInt("salary", 0)
                                    .toDouble()) > 100.0
                            ) "100.0%"
                            else String.format(
                                "%.1f",
                                (value * 100.0 / preferences.getInt("salary", 0).toDouble())
                            ).plus("%")
                        expenseViewModel.readExpenseData(month, year).observe(viewLifecycleOwner) {
                            var doubleValue = 0.0
                            for (j in it) {
                                doubleValue += j
                            }
                            binding.totalPerMonthNumber.text =
                                String.format("%.1f", value * 100.0 / doubleValue)
                                    .plus("%")
                        }
                    }
                    binding.totalNumber.text = value.toString()
                }
            }
            getString(R.string.travel) -> {
                try {
                    var value = 0
                    for (i in viewModel.readTravelData(month, year).value?.indices!!) {
                        value += viewModel.readTravelData(month, year).value!![i]
                        binding.indicatorText.text =
                            String.format("%.1f", value * 100.0 / preferences.getInt("salary", 0))
                    }
                } catch (e: Exception) {
                    binding.indicatorText.text = "0.0%"
                    binding.paymentIndicator.progress = 0
                }
                viewModel.readTravelData(month, year).observe(viewLifecycleOwner) { it ->
                    var value = 0
                    for (i in it.indices) {
                        value += it[i]
                        binding.paymentIndicator.progress =
                            if (kotlin.math.ceil((value * 100.0 / preferences.getInt("salary", 0)))
                                    .roundToInt() > preferences.getInt("salary", 0)
                            ) 100
                            else kotlin.math.ceil((value * 100.0 / preferences.getInt("salary", 0)))
                                .roundToInt()
                        binding.indicatorText.text =
                            if ((value * 100.0 / preferences.getInt("salary", 0)
                                    .toDouble()) > 100.0
                            ) "100.0%"
                            else String.format(
                                "%.1f",
                                (value * 100.0 / preferences.getInt("salary", 0).toDouble())
                            ).plus("%")
                        expenseViewModel.readExpenseData(month, year).observe(viewLifecycleOwner) {
                            var doubleValue = 0.0
                            for (j in it) {
                                doubleValue += j
                            }
                            binding.totalPerMonthNumber.text =
                                String.format("%.1f", value * 100.0 / doubleValue)
                                    .plus("%")
                        }
                    }
                    binding.totalNumber.text = value.toString()
                }
            }
            else -> {
                expenseViewModel.readExpenseData(month, year).observe(viewLifecycleOwner) {
                    var value = 0.0
                    for (i in it) {
                        value += i
                    }
                    binding.totalPerMonthNumber.text =
                        String.format("%.1f", preferences.getInt(args.category, 0) * 100.0 / value)
                            .plus("%")
                }
                binding.totalNumber.text = preferences.getInt(args.category, 0).toString()
                binding.indicatorText.text =
                    if ((preferences.getInt(args.category, 0) * 100.0 / preferences.getInt(
                            "salary",
                            0
                        )
                            .toDouble()) > 100.0
                    ) "100.0%"
                    else String.format(
                        "%.1f",
                        (preferences.getInt(args.category, 0) * 100.0 / preferences.getInt(
                            "salary",
                            0
                        ).toDouble())
                    ).plus("%")
                binding.paymentIndicator.progress = if (kotlin.math.ceil(
                        (preferences.getInt(
                            args.category,
                            0
                        ) * 100.0 / preferences.getInt("salary", 0))
                    ).roundToInt() > preferences.getInt("salary", 0)
                ) 100
                else kotlin.math.ceil(
                    (preferences.getInt(
                        args.category,
                        0
                    ) * 100.0 / preferences.getInt("salary", 0))
                ).roundToInt()
            }
        }
    }
}