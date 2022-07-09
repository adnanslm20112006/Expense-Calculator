package com.example.myapplication.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.currencies
import com.example.myapplication.currenciesCodes
import com.example.myapplication.database.expenses.ExpenseViewModel
import com.example.myapplication.databinding.FragmentTotalBinding
import com.example.myapplication.network.viewModel.CurrenciesViewModel
import com.example.myapplication.network.viewModel.factory.CurrenciesViewModelFactory
import com.google.android.material.snackbar.Snackbar
import org.json.JSONObject
import java.util.*


class Total : Fragment() {
    private lateinit var binding: FragmentTotalBinding
    private lateinit var preferences: SharedPreferences
    private lateinit var viewModel: CurrenciesViewModel
    private val expenseViewModel by viewModels<ExpenseViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTotalBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("InflateParams", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        preferences = requireActivity().getSharedPreferences("User", Context.MODE_PRIVATE)
        var rate = 0.0
        val c = Calendar.getInstance()
        val month = c[Calendar.MONTH] + 1
        val year = c[Calendar.YEAR]
        val installedMonth = preferences.getInt("installedMonth", month + 1)
        if (installedMonth < month + 1) {
            expenseViewModel.reaYearData(year).observe(viewLifecycleOwner) {
                var value = 0.0
                for (i in it) {
                    value += i
                }
                val rateResult = (value / ((month + 1) - installedMonth))
                binding.monthlyRateValue.text = rateResult.toString()
            }
            expenseViewModel.readExpenseData(month + 1, year).observe(viewLifecycleOwner) {
                var value = 0.0
                for (i in it) {
                    value += i
                }
                val result = (value - binding.monthlyRateValue.text.toString().toDouble())
                binding.paymentRateValue.text = result.toString()
            }
        }
        val codes = currenciesCodes.toSet()
        val currencies = currencies.map { it.lowercase(Locale.ROOT) }.toSet().also { set ->
            set.map { s -> s.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() } }.toSet()
        }
        binding.salaryValue.text = preferences.getInt("salary", 0).toString()
        val viewModelFactory = CurrenciesViewModelFactory()
        binding.from.text = preferences.getString("from", "TRY")
        binding.to.text = preferences.getString("to", "USD")
        viewModel = ViewModelProvider(this, viewModelFactory)[CurrenciesViewModel::class.java]
        viewModel.getJson()
        binding.from.setOnClickListener {
            AlertDialog.Builder(requireContext()).setTitle(R.string.select_currency)
                .setIcon(R.drawable.ic_currency)
                .setSingleChoiceItems(currencies.toTypedArray(), 0) { dialog: DialogInterface?, position: Int ->
                    binding.from.text = codes.toTypedArray()[position]
                    preferences.edit().putString("from", codes.toTypedArray()[position]).apply()
                    viewModel.getJson()
                    dialog?.dismiss()
                }.show()
        }
        binding.to.setOnClickListener {
            AlertDialog.Builder(requireContext()).setTitle(R.string.select_currency)
                .setIcon(R.drawable.ic_currency)
                .setSingleChoiceItems(currencies.toTypedArray(), 0) { dialog: DialogInterface?, position: Int ->
                    binding.to.text = codes.toTypedArray()[position]
                    preferences.edit().putString("to", codes.toTypedArray()[position]).apply()
                    viewModel.getJson()
                    dialog?.dismiss()
                }.show()
        }
        try {
            var value = 0.0
            for (i in expenseViewModel.readExpenseData(month, year).value?.indices!!) {
                value += expenseViewModel.readExpenseData(month, year).value!![i]
                if (value >= preferences.getInt("salary", 0)) {
                    binding.salaryRestValue.text = "0"
                    binding.paymentRateValue.text =
                        (value - preferences.getInt("salary", 0)).toString()
                } else {
                    binding.salaryRestValue.text =
                        (preferences.getInt("salary", 0) - value).toString()
                    binding.paymentRateValue.text = "0"
                }
            }
        } catch (e: Exception) {
            binding.salaryRestValue.text = preferences.getInt("salary", 0).toString()
        }
        expenseViewModel.readExpenseData(month, year).observe(viewLifecycleOwner) {
            var value = 0.0
            for (i in it) {
                value += i
                if (value >= preferences.getInt("salary", 0)) {
                    binding.salaryRestValue.text = "0"
                    binding.paymentRateValue.text =
                        (value - preferences.getInt("salary", 0)).toString()
                } else {
                    binding.salaryRestValue.text =
                        (preferences.getInt("salary", 0) - value).toString()
                    binding.paymentRateValue.text = "0"
                }
            }
        }
        viewModel.status.observe(viewLifecycleOwner) {
            val json = JSONObject(it)
            rate = json.getDouble(
                "${
                    preferences.getString(
                        "from",
                        "TRY"
                    )
                }_${preferences.getString("to", "TRY")}"
            )
            binding.toCurrency.setText(
                String.format(
                    "%.4f", rate.times(
                        binding.fromCurrency.text.toString().toDouble()
                    )
                )
            )
        }
        viewModel.code.observe(viewLifecycleOwner) {
            when (viewModel.code.value) {
                false -> {
                    binding.toCurrency.setText(R.string.error)
                    val snackBar = Snackbar.make(view, "", Snackbar.LENGTH_LONG)
                    val customView
                            : View =
                        requireActivity().layoutInflater.inflate(R.layout.snackbar_internet, null)
                    snackBar.view.setBackgroundColor(Color.TRANSPARENT)
                    val snackBarView = snackBar.view as Snackbar.SnackbarLayout
                    snackBarView.addView(customView, 0)
                    snackBar.show()
                }
                else -> {
                }
            }
        }
        val username = "${
            preferences.getString("name", "")
                ?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
        } ${
            preferences.getString("surname", "")?.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.ROOT
                ) else it.toString()
            }
        }"
        binding.nameUser.text = username
        val currencyTextWatcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                viewModel.status.observe(viewLifecycleOwner) {
                    when (viewModel.code.value) {
                        true -> {
                            if (binding.fromCurrency.text.isNotEmpty()) {
                                binding.toCurrency.setText(
                                    String.format(
                                        "%.4f", rate.times(
                                            binding.fromCurrency.text.toString().toDouble()
                                        )
                                    )
                                )
                            } else {
                                binding.toCurrency.setText(R.string.error)
                            }
                        }
                        else -> {
                            binding.toCurrency.setText(R.string.error)
                            val snackBar = Snackbar.make(view, "", Snackbar.LENGTH_LONG)
                            val customView
                                    : View =
                                requireActivity().layoutInflater.inflate(
                                    R.layout.snackbar_internet,
                                    null
                                )
                            snackBar.view.setBackgroundColor(Color.TRANSPARENT)
                            val snackBarView = snackBar.view as Snackbar.SnackbarLayout
                            snackBarView.addView(customView, 0)
                            snackBar.show()
                        }
                    }
                }
            }
        }
        binding.fromCurrency.addTextChangedListener(currencyTextWatcher)
    }
}
