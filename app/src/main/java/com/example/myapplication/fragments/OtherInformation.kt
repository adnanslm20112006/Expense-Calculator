package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.database.otherCategories.OtherCategoriesViewModel
import com.example.myapplication.databinding.FragmentOtherInformationBinding
import com.example.myapplication.recyclerView.otherCategories.OtherCategoriesAdapter

class OtherInformation : Fragment() {
    private lateinit var binding: FragmentOtherInformationBinding
    private lateinit var mViewModel: OtherCategoriesViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOtherInformationBinding.inflate(inflater, container, false)
        mViewModel = ViewModelProvider(this)[OtherCategoriesViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = OtherCategoriesAdapter(findNavController())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        mViewModel.readAllData.observe(viewLifecycleOwner) { otherCategory ->
            adapter.setData(otherCategory)
            if (adapter.itemCount == 0) {
                binding.sadFace.visibility = View.VISIBLE
                binding.sadFaceText.visibility = View.VISIBLE
            }
            else {
                binding.sadFace.visibility = View.GONE
                binding.sadFaceText.visibility = View.GONE
            }
        }
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_otherInformation_to_addOtherCategory)
        }
    }
}