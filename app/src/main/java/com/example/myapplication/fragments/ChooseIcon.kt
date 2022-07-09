package com.example.myapplication.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.TinyDB
import com.example.myapplication.database.otherCategories.OtherCategoriesData
import com.example.myapplication.database.otherCategories.OtherCategoriesViewModel
import com.example.myapplication.databinding.FragmentChooseIconBinding
import com.example.myapplication.recyclerView.otherCategoryIcon.OtherCategoryIcon
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.SnackbarLayout

class ChooseIcon : Fragment() {
    private lateinit var binding: FragmentChooseIconBinding
    private lateinit var preferences: SharedPreferences
    private val viewModel: OtherCategoriesViewModel by viewModels()
    private val args: ChooseIconArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentChooseIconBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("InflateParams")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        preferences = requireActivity().getSharedPreferences("User", Context.MODE_PRIVATE)
        preferences.edit().putInt("selectedIcon", 0).apply()
        val adapter = OtherCategoryIcon()
        binding.iconsList.adapter = adapter
        binding.iconsList.layoutManager = GridLayoutManager(context, 4)
        binding.saveButton.setOnClickListener {
            if (preferences.getInt("selectedIcon", 0) != 0) {
                val otherCategory =
                    OtherCategoriesData(0, preferences.getInt("selectedIcon", 0), args.categoryName)
                viewModel.addCategory(otherCategory)
                Handler(Looper.getMainLooper()).postDelayed({
                    findNavController().navigate(R.id.action_chooseIcon_to_otherInformation)
                }, 500)
            }
            else {
                val snackBar = Snackbar.make(view, "", Snackbar.LENGTH_LONG)
                val customView: View = requireActivity().layoutInflater.inflate(R.layout.snackbar, null)
                snackBar.view.setBackgroundColor(Color.TRANSPARENT)
                val snackBarView = snackBar.view as SnackbarLayout
                snackBarView.addView(customView, 0)
                snackBar.show()
            }
        }
    }
}