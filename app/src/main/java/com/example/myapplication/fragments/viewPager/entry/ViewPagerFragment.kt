package com.example.myapplication.fragments.viewPager.entry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentViewPagerBinding
import com.example.myapplication.viewPager.ViewPagerAdapter

class ViewPagerFragment : Fragment() {
    private lateinit var binding: FragmentViewPagerBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         binding = FragmentViewPagerBinding.inflate(layoutInflater, container, false)
        val adapter = ViewPagerAdapter(requireActivity().supportFragmentManager , 1)
        binding.viewPager.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}
