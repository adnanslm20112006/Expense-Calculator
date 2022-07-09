package com.example.myapplication.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSplashScreenBinding


@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment() {
    private lateinit var preferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        preferences = requireActivity().getSharedPreferences("User", Context.MODE_PRIVATE)
        if (Build.VERSION.SDK_INT < 32) {
            Handler(Looper.getMainLooper()).postDelayed({
                if (preferences.getBoolean("isFirst", true)) {
                    findNavController().navigate(R.id.action_splashScreen_to_viewPagerFragment)
                } else {
                    findNavController().navigate(R.id.action_splashScreen_to_appUiActivity2)
                    activity?.finish()
                }
            }, 3000)
        }
        else {
            if (preferences.getBoolean("isFirst", true)) {
                findNavController().navigate(R.id.action_splashScreen_to_viewPagerFragment)
            } else {
                findNavController().navigate(R.id.action_splashScreen_to_appUiActivity2)
                activity?.finish()
            }
        }
        return binding.root
    }
}