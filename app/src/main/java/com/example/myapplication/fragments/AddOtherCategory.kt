package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.database.otherCategories.OtherCategoriesViewModel
import com.example.myapplication.databinding.AddOtherCategoryBinding

class AddOtherCategory: Fragment() {
    private lateinit var binding: AddOtherCategoryBinding
    private lateinit var mViewModel: OtherCategoriesViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddOtherCategoryBinding.inflate(inflater, container, false)
        mViewModel = ViewModelProvider(this)[OtherCategoriesViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.okButton.setOnClickListener {
            if (checkCategory()) {
                val categoryName = binding.addCategory.text.toString()
                val action = AddOtherCategoryDirections.actionAddOtherCategoryToChooseIcon(categoryName)
                findNavController().navigate(action)
            }
        }
    }
    private fun checkCategory(): Boolean {
        return if (binding.addCategory.text.isEmpty() || binding.addCategory.text.toString() == "") {
            binding.addCategory.error = getString(R.string.please_add_category)
            false
        }
        else {
            true
        }
    }
}