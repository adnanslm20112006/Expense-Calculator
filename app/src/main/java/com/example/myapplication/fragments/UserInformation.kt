package com.example.myapplication.fragments

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.*
import com.example.myapplication.databinding.FragmentUserInformationBinding
import java.util.*

class UserInformation : Fragment() {
    private lateinit var binding: FragmentUserInformationBinding
    private lateinit var preferences: SharedPreferences
    val array = regions_list.sortedArray()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tinyDB = TinyDB(requireActivity().applicationContext)
        preferences = requireActivity().getSharedPreferences("User", Context.MODE_PRIVATE)
        binding.birthday.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c[Calendar.YEAR]
            val month = c[Calendar.MONTH]
            val dayOfMonth = c[Calendar.DAY_OF_MONTH]
            val datePicker = DatePickerDialog(requireContext(), {
                _: DatePicker?, Year: Int, Month: Int, Day: Int ->
                binding.birthday.text = "$Day/${Month + 1}/$Year"
            }, year, month, dayOfMonth)
            datePicker.show()
        }
        binding.region.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setSingleChoiceItems(array, 0) { dialog: DialogInterface?, position: Int ->
                    val region = array[position]; binding.region.text = region
                    dialog?.dismiss()
                    binding.errorText.visibility = View.INVISIBLE
                    binding.errorIc.visibility = View.INVISIBLE
                }.setTitle(R.string.select_your_origin).setIcon(R.drawable.ic_origin).show()
        }
        binding.next.setOnClickListener {
            if (layoutCheck()) {
                val c = Calendar.getInstance()
                val month = c[Calendar.MONTH]
                val editor = preferences.edit()
                editor.putBoolean("isFirst", false)
                editor.putString("birthday", binding.birthday.text.toString())
                editor.putString("name", binding.name.text.toString())
                editor.putString("surname", binding.surname.text.toString())
                editor.putInt("salary", Integer.parseInt(binding.salary.text.toString()))
                editor.putString("region", binding.region.text.toString())
                editor.putInt("installedMonth", month + 1)
                for (i in currenciesCodes.indices) {
                    if (array[i] == binding.region.text.toString()) {
                        editor.putString("from", currenciesCodes[i])
                        editor.putString("to", "USD")
                    }
                }
                editor.apply()
                tinyDB.putListString("otherCategoriesString", arrayListOf("Category"))
                tinyDB.putListInt("otherCategoriesImage", arrayListOf(R.drawable.ic_category_big))
                findNavController().navigate(R.id.action_userInformation_to_appUiActivity2)
                activity?.finish()
            }
        }
    }

    private fun checkName(): Boolean {
        return when {
            TextUtils.isEmpty(binding.name.text) -> {
                binding.name.error = getString(R.string.add_your_name)
                false
            }
            else -> {
                binding.name.error = ""
                true
            }
        }
    }
    private fun checkSurname(): Boolean {
        return when {
            TextUtils.isEmpty(binding.surname.text) -> {
                binding.surname.error = getString(R.string.add_your_surname)
                false
            }
            else -> {
                binding.surname.error = ""
                true
            }
        }
    }
    private fun checkSalary(): Boolean {
        return when {
            TextUtils.isEmpty(binding.salary.text) -> {
                binding.salary.error = getString(R.string.add_your_salary)
                false
            }
            else -> {
                binding.salary.error = ""
                true
            }
        }
    }

    private fun checkRegion(): Boolean {
        return when (binding.region.text) {
            getString(R.string.origin) -> {
                binding.errorText.visibility = View.VISIBLE
                binding.errorIc.visibility = View.VISIBLE
                false
            }
            else -> {
                binding.errorText.visibility = View.INVISIBLE
                binding.errorIc.visibility = View.INVISIBLE
                true
            }
        }
    }

    private fun layoutCheck(): Boolean {
        checkName()
        checkSurname()
        checkSalary()
        checkRegion()
        checkBirthday()
        return checkName() && checkSalary() && checkRegion() && checkSurname() && checkBirthday()
    }

    private fun checkBirthday(): Boolean {
        return if (binding.birthday.text == getString(R.string.birthday)) {
            binding.errorIcBirthday.visibility = View.VISIBLE
            binding.errorTextBirthday.visibility = View.VISIBLE
            false
        }
        else {
            binding.errorTextBirthday.visibility = View.INVISIBLE
            binding.errorIcBirthday.visibility = View.INVISIBLE
            true
        }
    }
}
