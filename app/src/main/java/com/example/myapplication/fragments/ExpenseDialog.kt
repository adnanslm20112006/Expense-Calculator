package com.example.myapplication.fragments

import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.DatePicker
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.database.categories.*
import com.example.myapplication.database.expenses.Expense
import com.example.myapplication.database.expenses.ExpenseViewModel
import com.example.myapplication.database.otherCategories.OtherCategoriesViewModel
import com.example.myapplication.databinding.ExpenseDialogBinding
import com.example.myapplication.listOtherCategories
import com.example.myapplication.spinner.expense.Categories
import com.example.myapplication.spinner.expense.LabelArrayAdapter
import com.example.myapplication.spinner.other.OtherLabel
import com.example.myapplication.spinner.other.OtherLabelArrayAdapter
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.ArrayList


class ExpenseDialog : Fragment() {
    private lateinit var binding: ExpenseDialogBinding
    private lateinit var mViewModel: ExpenseViewModel
    private lateinit var viewModel: CategoriesViewModel
    private lateinit var category: String
    private lateinit var subCategory: String
    private lateinit var preferences: SharedPreferences
    private lateinit var list: ArrayList<OtherLabel>
    private val stateViewModel: StateViewModel by viewModels()
    private val otherViewModel: OtherCategoriesViewModel by viewModels()
    val c: Calendar = Calendar.getInstance()
    val month = c[Calendar.MONTH] + 1
    val year = c[Calendar.YEAR]
    private val decimalNumber = Regex("^[1-9]\\d{0,2}(.\\d+)?\$")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ExpenseDialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        list = arrayListOf(OtherLabel(R.drawable.ic_category, getString(R.string.category)))
        otherViewModel.readAllData.observe(viewLifecycleOwner)
        {
            for (i in it) {
                list.add(OtherLabel(i.imageId, i.text))
            }
        }
        preferences = requireActivity().getSharedPreferences("User", Context.MODE_PRIVATE)
        setUpSpinner()
        customSpinner()
        stateViewModel.dateState.observe(viewLifecycleOwner)
        {
            if (!it.date.isNullOrEmpty()) {
                binding.expenseDate.text = it.date
            }
        }
        stateViewModel.otherErrorState.observe(viewLifecycleOwner)
        {
            if (it == false)
                binding.otherError.visibility = View.VISIBLE
            else
                binding.otherError.visibility = View.GONE
        }
        stateViewModel.errorState.observe(viewLifecycleOwner)
        {
            if (it == false)
                binding.error.visibility = View.VISIBLE
            else
                binding.error.visibility = View.GONE
        }
        stateViewModel.roundState.observe(viewLifecycleOwner)
        {
            binding.expenseRound.isChecked = it
        }

        val costTextWatcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                stateViewModel.changeCostState(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        }
        binding.expenseCost.addTextChangedListener(costTextWatcher)
        binding.expenseRound.setOnCheckedChangeListener { _, isChecked ->
            stateViewModel.changeRoundState(isChecked)
        }
        binding.otherCategory.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (Categories.list!![binding.otherCategory.selectedItemPosition].name) {
                        Categories.list!![0].name -> {
                            stateViewModel.changeOtherState(false)
                        }
                        "Category" -> {
                            stateViewModel.changeOtherState(false)
                        }
                        else -> {
                            subCategory =
                                Categories.list!![binding.otherCategory.selectedItemPosition].name
                            stateViewModel.changeOtherState(true)
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    if (Categories.list!![binding.expenseCategory.selectedItemPosition].name == "Category") {
                        stateViewModel.changeOtherState(false)
                    }
                }

            }
        binding.expenseCategory.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (Categories.list!![binding.expenseCategory.selectedItemPosition].name) {
                        Categories.list!![0].name -> {
                            binding.view2.visibility = View.GONE
                            binding.otherCategory.visibility = View.GONE
                            binding.arrowOther.visibility = View.GONE
                            stateViewModel.changeState(false)
                            stateViewModel.changeOtherState(true)
                        }
                        "Other" -> {
                            if (binding.otherCategory.visibility != View.VISIBLE) {
                                binding.view2.visibility = View.VISIBLE
                                binding.otherCategory.visibility = View.VISIBLE
                                binding.arrowOther.visibility = View.VISIBLE
                                category =
                                    Categories.list!![binding.expenseCategory.selectedItemPosition].name
                                stateViewModel.changeState(true)
                            }
                        }
                        else -> {
                            binding.view2.visibility = View.GONE
                            binding.otherCategory.visibility = View.GONE
                            binding.arrowOther.visibility = View.GONE
                            category =
                                Categories.list!![binding.expenseCategory.selectedItemPosition].name
                            stateViewModel.changeState(true)
                            stateViewModel.changeOtherState(true)
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    if (Categories.list!![binding.expenseCategory.selectedItemPosition].name == "Category") {
                        binding.error.visibility = View.VISIBLE
                        stateViewModel.changeState(false)
                        stateViewModel.changeOtherState(true)
                    }
                }
            }
        binding.expenseDate.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c[Calendar.YEAR]
            val month = c[Calendar.MONTH]
            val dayOfMonth = c[Calendar.DAY_OF_MONTH]
            val datePicker = DatePickerDialog(
                requireContext(),
                { _: DatePicker?, Year: Int, Month: Int, DayOfMonth: Int ->
                    val date = "$DayOfMonth/${Month + 1}/$Year"
                    stateViewModel.changeDateState(Date(Month + 1, Year, date))
                },
                year,
                month,
                dayOfMonth
            )
            datePicker.show()
        }
        mViewModel = ViewModelProvider(this)[ExpenseViewModel::
        class.java]
        viewModel = ViewModelProvider(this)[CategoriesViewModel::
        class.java]
        binding.ok.setOnClickListener {
            checkLayout()
            if (checkLayout()) {
                insertDataToDatabase()
            }
        }
    }

    private fun checkDate(): Boolean {
        val pattern: Pattern =
            Pattern.compile("^(?:31([/\\-.])(?:0?[13578]|1[02])\\1|(?:29|30)([/\\-.])(?:0?[13-9]|1[0-2])\\2)(?:1[6-9]|[2-9]\\d)?\\d{2}\$|^29([/\\-.])0?2\\3(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:16|[2468][048]|[3579][26])00)\$|^(?:0?[1-9]|1\\d|2[0-8])([/\\-.])(?:0?[1-9]|1[0-2])\\4(?:1[6-9]|[2-9]\\d)\\d{2}\$")
        return when {
            TextUtils.isEmpty(binding.expenseDate.text) -> {
                binding.expenseDate.error = getString(R.string.date_check)
                false
            }
            !binding.expenseDate.text.matches(pattern.toRegex()) -> {
                binding.expenseDate.error = "Please use this format dd/mm/yyyy"
                false
            }
            else -> {
                binding.expenseDate.error = null
                true
            }
        }
    }

    private fun checkCost(): Boolean {
        return when {
            TextUtils.isEmpty(binding.expenseCost.text) -> {
                binding.expenseCost.error = getString(R.string.cost_check)
                false
            }
            !binding.expenseCost.text.matches(decimalNumber) -> {
                binding.expenseCost.error = getString(R.string.incorrect_format)
                false
            }
            else -> {
                binding.expenseCost.error = null
                true
            }
        }
    }

    private fun checkLayout(): Boolean {
        checkDate()
        customSpinner()
        checkCost()
        customOtherSpinner()
        return checkDate() && customSpinner() && checkCost() && customOtherSpinner()
    }

    private fun insertDataToDatabase() {
        preferences = requireActivity().getSharedPreferences("User", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        val date = binding.expenseDate.text.toString()
        var cost = binding.expenseCost.text.toString()
        category =
            if (binding.expenseCategory.isVisible && !binding.otherCategory.isVisible)
                Categories.list!![binding.expenseCategory.selectedItemPosition].name
            else
                list[binding.otherCategory.selectedItemPosition].name
        subCategory = list[binding.otherCategory.selectedItemPosition].name
        if (stateViewModel.roundState.value!!) {
            val roundedCost = kotlin.math.ceil(cost.toDouble())
            cost = roundedCost.toString()
        }
        val expense = Expense(
            0,
            date,
            category,
            cost.toDouble(),
            stateViewModel.dateState.value?.month!!,
            stateViewModel.dateState.value?.year!!
        )
        mViewModel.addExpense(expense)
        when (category) {
            getString(R.string.restaurant) -> {
                val restaurant = RestaurantData(
                    0,
                    Integer.parseInt(kotlin.math.ceil(cost.toDouble()).toInt().toString()),
                    stateViewModel.dateState.value?.month!!,
                    stateViewModel.dateState.value?.year!!
                )
                viewModel.addRestaurant(restaurant)
            }
            getString(R.string.transport) -> {
                val transport = TransportData(
                    0,
                    Integer.parseInt(kotlin.math.ceil(cost.toDouble()).toInt().toString()),
                    stateViewModel.dateState.value?.month!!,
                    stateViewModel.dateState.value?.year!!
                )
                viewModel.addTransport(transport)
            }
            getString(R.string.health) -> {
                val health = HealthData(
                    0,
                    Integer.parseInt(kotlin.math.ceil(cost.toDouble()).toInt().toString()),
                    stateViewModel.dateState.value?.month!!,
                    stateViewModel.dateState.value?.year!!
                )
                viewModel.addHealth(health)
            }
            getString(R.string.emergency) -> {
                val emergency = EmergencyData(
                    0,
                    Integer.parseInt(kotlin.math.ceil(cost.toDouble()).toInt().toString()),
                    stateViewModel.dateState.value?.month!!,
                    stateViewModel.dateState.value?.year!!
                )
                viewModel.addEmergency(emergency)
            }
            getString(R.string.travel) -> {
                val travel = TravelData(
                    0,
                    Integer.parseInt(kotlin.math.ceil(cost.toDouble()).toInt().toString()),
                    stateViewModel.dateState.value?.month!!,
                    stateViewModel.dateState.value?.year!!
                )
                viewModel.addTravel(travel)
            }
            getString(R.string.family) -> {
                val family = FamilyData(
                    0,
                    Integer.parseInt(kotlin.math.ceil(cost.toDouble()).toInt().toString()),
                    stateViewModel.dateState.value?.month!!,
                    stateViewModel.dateState.value?.year!!
                )
                viewModel.addFamily(family)
            }
            getString(R.string.entertainment) -> {
                val entertainment = EntertainmentData(
                    0,
                    Integer.parseInt(kotlin.math.ceil(cost.toDouble()).toInt().toString()),
                    stateViewModel.dateState.value?.month!!,
                    stateViewModel.dateState.value?.year!!
                )
                viewModel.addEntertainment(entertainment)
            }
            getString(R.string.gifts) -> {
                val gifts = GiftsData(
                    0,
                    Integer.parseInt(kotlin.math.ceil(cost.toDouble()).toInt().toString()),
                    stateViewModel.dateState.value?.month!!,
                    stateViewModel.dateState.value?.year!!
                )
                viewModel.addGifts(gifts)
            }
            getString(R.string.shopping) -> {
                val shopping = ShoppingData(
                    0,
                    kotlin.math.ceil(cost.toDouble()).toInt(),
                    stateViewModel.dateState.value?.month!!,
                    stateViewModel.dateState.value?.year!!
                )
                viewModel.addShopping(shopping)
            }
            else -> {
                val other = OtherData(
                    0,
                    kotlin.math.ceil(cost.toDouble()).toInt(),
                    stateViewModel.dateState.value?.month!!,
                    stateViewModel.dateState.value?.year!!
                )
                val value = preferences.getInt(
                    subCategory,
                    0
                ) + Integer.parseInt(kotlin.math.ceil(cost.toDouble()).toInt().toString())
                editor.putInt(subCategory, value)
                editor.apply()
                viewModel.addOther(other)
            }
        }
        findNavController().navigate(R.id.action_expenseDialog_to_details2)
        val params = CoordinatorLayout.LayoutParams(
            CoordinatorLayout.LayoutParams.MATCH_PARENT,
            CoordinatorLayout.LayoutParams.MATCH_PARENT
        )
        val r: Resources = resources
        val px = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            55F,
            r.displayMetrics
        ).toInt()
        params.setMargins(0, 0, 0, px)
        val bottomAppBar = requireActivity().findViewById<BottomAppBar>(R.id.bottom_bar)
        requireActivity().findViewById<FragmentContainerView>(R.id.ui_fragment)?.layoutParams =
            params
        bottomAppBar.visibility = View.VISIBLE
        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.blink)
        bottomAppBar.startAnimation(animation)
        requireActivity().findViewById<FloatingActionButton>(R.id.floatingActionButton).show()

    }

    private fun customSpinner(): Boolean {
        return stateViewModel.errorState.value!!
    }

    private fun customOtherSpinner(): Boolean {
        return stateViewModel.otherErrorState.value!!
    }

    private fun setUpSpinner() {
        val adapter = LabelArrayAdapter(requireContext(), Categories.list!!)
        val otherAdapter = OtherLabelArrayAdapter(requireContext(), list)
        binding.expenseCategory.adapter = adapter
        binding.otherCategory.adapter = otherAdapter
    }

    override fun onStart() {
        super.onStart()
        val params = CoordinatorLayout.LayoutParams(
            CoordinatorLayout.LayoutParams.MATCH_PARENT,
            CoordinatorLayout.LayoutParams.MATCH_PARENT
        )
        params.setMargins(0, 0, 0, 0)
        requireActivity().findViewById<FragmentContainerView>(R.id.ui_fragment).layoutParams =
            params
        requireActivity().findViewById<FloatingActionButton>(R.id.floatingActionButton).hide()
        stateViewModel.dateState.value?.date.let {
            if (!it.isNullOrEmpty()) {
                binding.expenseDate.text = it
            }
        }
        binding.expenseRound.isChecked = stateViewModel.roundState.value!!
        stateViewModel.costState.value.let {
            if (!it.isNullOrEmpty()) {
                binding.expenseCost.setText(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        listOtherCategories = list
    }
}

class StateViewModel : ViewModel() {

    private var _otherErrorState = MutableLiveData(true)
    val otherErrorState get() = _otherErrorState
    fun changeOtherState(state: Boolean) {
        _otherErrorState.value = state
    }

    private var _errorState = MutableLiveData(true)
    val errorState get() = _errorState
    fun changeState(state: Boolean) {
        _errorState.value = state
    }

    private var _dateState = MutableLiveData(Date(null, null, null))
    val dateState get() = _dateState
    fun changeDateState(state: Date) {
        _dateState.value = state
    }

    private var _costState = MutableLiveData<String?>(null)
    val costState = _costState
    fun changeCostState(state: String) {
        _costState.value = state
    }

    private var _roundState = MutableLiveData(false)
    val roundState get() = _roundState
    fun changeRoundState(state: Boolean) {
        _roundState.value = state
    }
}

data class Date(val month: Int?, val year: Int?, val date: String?)