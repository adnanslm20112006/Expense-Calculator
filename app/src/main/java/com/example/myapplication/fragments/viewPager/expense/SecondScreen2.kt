package com.example.myapplication.fragments.viewPager.expense

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.database.categories.CategoriesViewModel
import com.example.myapplication.databinding.FragmentPieChartBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import java.util.*
import kotlin.collections.ArrayList

class SecondScreen2 : Fragment() {
    private lateinit var binding: FragmentPieChartBinding
    private lateinit var preferences: SharedPreferences
    private lateinit var viewModel: CategoriesViewModel
    private var restaurantValue = 0F
    private var shoppingValue = 0F
    private var transportValue = 0F
    private var healthValue = 0F
    private var familyValue = 0F
    private var giftsValue = 0F
    private var entertainmentValue = 0F
    private var emergencyValue = 0F
    private var travelValue = 0F
    private var otherValue = 0F

    private val categoriesMap = mutableMapOf<String, Float>()

    companion object {
        val multipleColorTemplate: IntArray = intArrayOf(
            Color.rgb(255, 214, 0), Color.rgb(25, 118, 210),
            Color.rgb(211, 47, 47), Color.rgb(67, 160, 71),
            Color.rgb(69, 90, 100), Color.rgb(94, 53, 225),
            Color.rgb(194, 24, 91), Color.rgb(156, 39, 126),
            Color.rgb(121, 134, 203), Color.rgb(0, 77, 64)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPieChartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val c = Calendar.getInstance()
        val month = c[Calendar.MONTH]
        val year = c[Calendar.YEAR]
        viewModel = ViewModelProvider(this)[CategoriesViewModel::class.java]
        preferences = requireActivity().getSharedPreferences("User", Context.MODE_PRIVATE)
        viewModel.run {
            readRestaurantData(month + 1, year)
            readShoppingData(month + 1, year)
            readTransportData(month + 1, year)
            readHealthData(month + 1, year)
            readFamilyData(month + 1, year)
            readGiftsData(month + 1, year)
            readEntertainmentData(month + 1, year)
            readEmergencyData(month + 1, year)
            readTravelData(month + 1, year)
        }
        checkPieChart(categoriesMap)
        viewModel.readRestaurantData(month + 1, year).observe(viewLifecycleOwner) {
            for (i in it) {
                restaurantValue += i.toFloat()
                categoriesMap[getString(R.string.restaurant)] = restaurantValue
                checkPieChart(categoriesMap)
                setUpPieChart(categoriesMap)
            }
        }
        viewModel.readShoppingData(month + 1, year).observe(viewLifecycleOwner) {
            for (i in it) {
                shoppingValue += i.toFloat()
                categoriesMap[getString(R.string.shopping)] = shoppingValue
                checkPieChart(categoriesMap)
                setUpPieChart(categoriesMap)
            }
        }
        viewModel.readTransportData(month + 1, year).observe(viewLifecycleOwner) {
            for (i in it) {
                transportValue += i.toFloat()
                categoriesMap[getString(R.string.transport)] = transportValue
                checkPieChart(categoriesMap)
                setUpPieChart(categoriesMap)
            }
        }
        viewModel.readHealthData(month + 1, year).observe(viewLifecycleOwner) {
            for (i in it) {
                healthValue += i.toFloat()
                categoriesMap[getString(R.string.health)] = healthValue
                checkPieChart(categoriesMap)
                setUpPieChart(categoriesMap)
            }
        }
        viewModel.readFamilyData(month + 1, year).observe(viewLifecycleOwner) {
            for (i in it) {
                familyValue += i.toFloat()
                categoriesMap[getString(R.string.family)] = familyValue
                checkPieChart(categoriesMap)
                setUpPieChart(categoriesMap)
            }
        }
        viewModel.readGiftsData(month + 1, year).observe(viewLifecycleOwner) {
            for (i in it) {
                giftsValue += i.toFloat()
                categoriesMap[getString(R.string.gifts)] = giftsValue
                checkPieChart(categoriesMap)
                setUpPieChart(categoriesMap)
            }
        }
        viewModel.readEntertainmentData(month + 1, year).observe(viewLifecycleOwner) {
            for (i in it) {
                entertainmentValue += i.toFloat()
                categoriesMap[getString(R.string.entertainment)] = entertainmentValue
                checkPieChart(categoriesMap)
                setUpPieChart(categoriesMap)
            }
        }
        viewModel.readEmergencyData(month + 1, year).observe(viewLifecycleOwner) {
            for (i in it) {
                emergencyValue += i.toFloat()
                categoriesMap[getString(R.string.emergency)] = emergencyValue
                checkPieChart(categoriesMap)
                setUpPieChart(categoriesMap)
            }
        }
        viewModel.readTravelData(month + 1, year).observe(viewLifecycleOwner) {
            for (i in it) {
                travelValue += i.toFloat()
                categoriesMap[getString(R.string.travel)] = travelValue
                checkPieChart(categoriesMap)
                setUpPieChart(categoriesMap)
            }
        }
        viewModel.readOtherData(month + 1, year).observe(viewLifecycleOwner) {
            for (i in it) {
                otherValue += i.toFloat()
                categoriesMap[getString(R.string.other)] = otherValue
                checkPieChart(categoriesMap)
                setUpPieChart(categoriesMap)
            }
        }
    }

    private fun setUpPieChart(map: MutableMap<String, Float>) {
        var loop = 0
        val pieChart = binding.chartView
        pieChart.setUsePercentValues(true)
        pieChart.description.isEnabled = false
        pieChart.animateY(1000, Easing.EaseInCubic)
        pieChart.setExtraOffsets(5f, 10f, 5f, 5f)
        pieChart.dragDecelerationFrictionCoef = 0.95f
        pieChart.isDrawHoleEnabled = true
        pieChart.setHoleColor(Color.WHITE)
        pieChart.transparentCircleRadius = 61f
        val yValues: ArrayList<PieEntry> = arrayListOf()
        for (i in map) {
            if ( i.value != 0F) {
                yValues.add(PieEntry(i.value, i.key))
            }
            ++loop
        }
        val dataSet = PieDataSet(yValues, "Categories")
        dataSet.sliceSpace = 3f
        dataSet.selectionShift = 5f
        dataSet.setColors(multipleColorTemplate, 1000)
        val data = PieData(dataSet)
        data.setValueTextColor(Color.BLACK)
        data.setValueTextSize(10f)
        pieChart.data = data
    }

    private fun checkPieChart(map: MutableMap<String, Float>) {
        var total = 0F
        for (i in map) {
            total += i.value
        }
        if (total == 0F) {
            binding.chartView.visibility = View.INVISIBLE
            binding.textEmpty.visibility = View.VISIBLE
        } else {
            binding.chartView.visibility = View.VISIBLE
            binding.textEmpty.visibility = View.INVISIBLE
        }
    }
}