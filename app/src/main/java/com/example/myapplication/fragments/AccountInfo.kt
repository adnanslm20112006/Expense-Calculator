package com.example.myapplication.fragments

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.content.res.Resources
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.DatePicker
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.AccountInfoBinding
import com.example.myapplication.regions_list
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*


class AccountInfo : Fragment() {
    private lateinit var binding: AccountInfoBinding
    private lateinit var preferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AccountInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences = requireActivity().getSharedPreferences("User", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        binding.theName.text = "${preferences.getString("name", "")} ${preferences.getString("surname", "")}"
        binding.theBirthday.text = preferences.getString("birthday", "")
        binding.theSalary.setText(preferences.getInt("salary", 0).toString())
        binding.nameEdit.setText(preferences.getString("name", ""))
        binding.surname.setText(preferences.getString("surname", ""))
        binding.theCountry.text = preferences.getString("region", "")
        val c = Calendar.getInstance()
        val mYear = c[Calendar.YEAR]
        val mMonth = c[Calendar.MONTH]
        val mDayOfMonth = c[Calendar.DAY_OF_MONTH]
        binding.theBirthday.setOnClickListener {
            DatePickerDialog(requireContext(), { _: DatePicker?, year: Int, month: Int, day: Int ->
                val birthday = "$day/${month + 1}/$year"
                binding.theBirthday.text = birthday
                editor.putString("birthday", birthday)
                editor.apply()
            }, mYear, mMonth, mDayOfMonth).show()
        }
        val list = regions_list.sortedArray()
        binding.theCountry.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle(R.string.select_your_origin)
                .setIcon(R.drawable.ic_origin)
                .setSingleChoiceItems(list, 0)
                { dialog: DialogInterface?, position: Int ->
                    val region = list[position]
                    binding.theCountry.text = region
                    editor.putString("region", region)
                    editor.apply()
                    dialog?.dismiss()
                }.show()
        }
        val nameTextWatcher: TextWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                val name = s.toString(); binding.theName.text = "$name ${binding.surname.text}"
                editor.putString("name", name)
                editor.apply()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        }
        val surnameTextWatcher: TextWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                val surname = s.toString(); binding.theName.text = "${binding.nameEdit.text} $surname"
                editor.putString("surname", surname)
                editor.apply()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        }
        val salaryTextWatcher: TextWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                var salary = s.toString()
                if (salary.isEmpty()) salary = "0"
                editor.putInt("salary", Integer.parseInt(salary))
                editor.apply()
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        }
        binding.nameEdit.addTextChangedListener(nameTextWatcher)
        binding.theSalary.addTextChangedListener(salaryTextWatcher)
        binding.surname.addTextChangedListener(surnameTextWatcher)
        binding.save.setOnClickListener {
            editor.putString("name", binding.nameEdit.text.toString())
            editor.putString("surname", binding.surname.text.toString())
            editor.putInt("salary", Integer.parseInt(binding.theSalary.text.toString()))
            editor.putString("birthday", binding.theBirthday.text.toString())
            editor.putString("region", binding.theCountry.text.toString())
            editor.apply()
            findNavController().navigate(R.id.action_accountInfo_to_details2)
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
            params.setMargins(0,0,0,px)
            val bottomAppBar = requireActivity().findViewById<BottomAppBar>(R.id.bottom_bar)
            requireActivity().findViewById<FragmentContainerView>(R.id.ui_fragment)?.layoutParams = params
            bottomAppBar.visibility = View.VISIBLE
            val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.blink)
            bottomAppBar.startAnimation(animation)
            requireActivity().findViewById<FloatingActionButton>(R.id.floatingActionButton).show()
        }
    }
}