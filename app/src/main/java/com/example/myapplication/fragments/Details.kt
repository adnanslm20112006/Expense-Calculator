package com.example.myapplication.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.database.expenses.ExpenseViewModel
import com.example.myapplication.recyclerView.details.ExpensesDate
import com.example.myapplication.databinding.FragmentDetailsBinding
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class Details : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var preferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        recyclerView = binding.expenseRecyclerView
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val c = Calendar.getInstance()
        var month = c[Calendar.MONTH] + 1
        var year = c[Calendar.YEAR]
        preferences = requireActivity().getSharedPreferences("User", Context.MODE_PRIVATE)
        val mViewModel = ViewModelProvider(this)[ExpenseViewModel::class.java]
        val adapter = ExpensesDate()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val current = setMonthText(month) + ", " + setYearText(year)
        binding.monthText.text = current
        setMonthText(month)
        binding.accountIc.setOnClickListener {
            val bottomAppBar = requireActivity().findViewById<BottomAppBar>(R.id.bottom_bar)
            val floatingActionButton = requireActivity().findViewById<FloatingActionButton>(R.id.floatingActionButton)
            val fragment = requireActivity().findViewById<FragmentContainerView>(R.id.ui_fragment)
            val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.fading)
            bottomAppBar.startAnimation(animation)
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {
                    Handler(Looper.getMainLooper()).postDelayed({
                        val params = CoordinatorLayout.LayoutParams(
                            CoordinatorLayout.LayoutParams.MATCH_PARENT,
                            CoordinatorLayout.LayoutParams.MATCH_PARENT
                        )
                        params.setMargins(0, 0, 0, 0)
                        fragment?.layoutParams = params
                    }, 400)
                    findNavController().navigate(R.id.action_details2_to_accountInfo)
                }

                override fun onAnimationEnd(animation: Animation) {
                    bottomAppBar.visibility = View.INVISIBLE
                }

                override fun onAnimationRepeat(animation: Animation) {}
            })
            floatingActionButton.hide()
        }
        binding.leftButton.setOnClickListener {
            if (month > 1) {
                month -= 1
                val currentDate = setMonthText(month) + ", " + setYearText(year)
                binding.monthText.text = currentDate
                mViewModel.readAllData(month, year).observe(viewLifecycleOwner) { expense ->
                    adapter.setData(expense)
                    if (adapter.itemCount == 0) {
                        binding.noExpenses.visibility = View.VISIBLE
                    } else {
                        binding.noExpenses.visibility = View.GONE
                    }
                }
            }
            else {
                month = 12
                year -= 1
                val currentDate = setMonthText(month) + ", " + setYearText(year)
                binding.monthText.text = currentDate
                mViewModel.readAllData(month, year).observe(viewLifecycleOwner) { expense ->
                    adapter.setData(expense)
                    if (adapter.itemCount == 0) {
                        binding.noExpenses.visibility = View.VISIBLE
                    } else {
                        binding.noExpenses.visibility = View.GONE
                    }
                }
            }
        }
        binding.rightButton.setOnClickListener {
            if (month < 12) {
                month += 1
                val currentDate = setMonthText(month) + ", " + setYearText(year)
                binding.monthText.text = currentDate
                mViewModel.readAllData(month, year).observe(viewLifecycleOwner) { expense ->
                    adapter.setData(expense)
                    if (adapter.itemCount == 0) {
                        binding.noExpenses.visibility = View.VISIBLE
                    } else {
                        binding.noExpenses.visibility = View.GONE
                    }
                }
            }
            else {
                month = 1
                year += 1
                val currentDate = setMonthText(month) + ", " + setYearText(year)
                binding.monthText.text = currentDate
                mViewModel.readAllData(month, year).observe(viewLifecycleOwner) { expense ->
                    adapter.setData(expense)
                    if (adapter.itemCount == 0) {
                        binding.noExpenses.visibility = View.VISIBLE
                    } else {
                        binding.noExpenses.visibility = View.GONE
                    }
                }
            }
        }
        mViewModel.readAllData(month, year).observe(viewLifecycleOwner) { expense ->
            adapter.setData(expense)
            if (adapter.itemCount == 0) {
                binding.noExpenses.visibility = View.VISIBLE
            } else {
                binding.noExpenses.visibility = View.GONE
            }
        }
        val hiName = "${getString(R.string.hi)} ${preferences.getString("name", "")
            ?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }}"
        binding.welcomeName.text = hiName
    }
    private fun setMonthText(month: Int): String {
        return when (month) {
            1 -> getString(R.string.january)
            2 -> getString(R.string.february)
            3 -> getString(R.string.march)
            4 -> getString(R.string.april)
            5 -> getString(R.string.may)
            6 -> getString(R.string.june)
            7 -> getString(R.string.july)
            8 -> getString(R.string.august)
            9 -> getString(R.string.september)
            10 -> getString(R.string.october)
            11 -> getString(R.string.november)
            else -> getString(R.string.december)
        }
    }
    private fun setYearText(year: Int): String {
        return year.toString()
    }
}
