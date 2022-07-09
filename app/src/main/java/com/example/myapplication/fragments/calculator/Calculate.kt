package com.example.myapplication.fragments.calculator

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentCalculateBinding
import org.mariuszgromada.math.mxparser.Expression

class Calculate : Fragment() {
    private var _binding: FragmentCalculateBinding? = null
    private val binding get() = _binding!!
    private var isEqualSelected = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalculateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.calculationText.showSoftInputOnFocus = false
        binding.btn0.setOnClickListener { zeroBTN() }
        binding.btn1.setOnClickListener { oneBTN() }
        binding.btn2.setOnClickListener { twoBTN() }
        binding.btn3.setOnClickListener { threeBTN() }
        binding.btn4.setOnClickListener { fourBTN() }
        binding.btn5.setOnClickListener { fiveBTN() }
        binding.btn6.setOnClickListener { sixBTN() }
        binding.btn7.setOnClickListener { sevenBTN() }
        binding.btn8.setOnClickListener { eightBTN() }
        binding.btn9.setOnClickListener { nineBTN() }
        binding.btnC.setOnClickListener { cBTN() }
        binding.btnDivide.setOnClickListener { divideBTN() }
        binding.btnMultiply.setOnClickListener { timesBTN() }
        binding.btnErase.setOnClickListener { eraseBTN() }
        binding.btnMines.setOnClickListener { minusBTN() }
        binding.btnPlus.setOnClickListener { plusBTN() }
        binding.btnParentheses.setOnClickListener { parenthesesBTN() }
        binding.btnEqual.setOnClickListener { equalBTN() }
        binding.btnDot.setOnClickListener { dotBTN() }
        binding.btnPlusMines.setOnClickListener { plusMinusBTN() }
    }

    private fun updateDisplay(str: String) {
        if (isEqualSelected) {
            binding.calculationText.setText("")
            isEqualSelected = false
        }
        val oldStr = binding.calculationText.text.toString()
        val cursorPos = binding.calculationText.selectionStart
        val leftStr = oldStr.substring(0, cursorPos)
        val rightStr = oldStr.substring(cursorPos)
        if (binding.calculationText.text.toString() == "") {
            binding.calculationText.setText(str)
            binding.calculationText.setSelection(cursorPos + 1)
        } else {
            binding.calculationText.setText(String.format("%s%s%s", leftStr, str, rightStr))
            binding.calculationText.setSelection(cursorPos + 1)
        }
    }

    private fun zeroBTN() { updateDisplay("0") }
    private fun oneBTN() { updateDisplay("1") }
    private fun twoBTN() { updateDisplay("2") }
    private fun threeBTN() { updateDisplay("3") }
    private fun fourBTN() { updateDisplay("4") }
    private fun fiveBTN() { updateDisplay("5") }
    private fun sixBTN() { updateDisplay("6") }
    private fun sevenBTN() { updateDisplay("7") }
    private fun eightBTN() { updateDisplay("8") }
    private fun nineBTN() { updateDisplay("9") }
    private fun cBTN() { binding.calculationText.setText("") }
    private fun divideBTN() { updateDisplay("÷") }
    private fun timesBTN() { updateDisplay("×") }
    private fun minusBTN() { updateDisplay("-") }
    private fun plusBTN() { updateDisplay("+") }

    private fun parenthesesBTN() {
        val cursorPos = binding.calculationText.selectionStart
        var openPar = 0
        var closePar = 0
        val textLength = binding.calculationText.length()
        var i = 0
        while (i < cursorPos) {
            if (binding.calculationText.text.toString().substring(i, i + 1) == "(") {
                openPar += 1
            }
            if (binding.calculationText.text.toString().substring(i, i + 1) == ")") {
                closePar += 1
            }
            i++
        }
        if (openPar == closePar || binding.calculationText.text.toString()
                .substring(textLength - 1, textLength) == "("
        ) {
            updateDisplay("(")
        } else if (closePar < openPar && binding.calculationText.text.toString()
                .substring(textLength - 1, textLength) != "("
        ) {
            updateDisplay(")")
        }
        binding.calculationText.setSelection(cursorPos + 1)
    }
    private fun equalBTN() {
        isEqualSelected = true
        var userExp: String = binding.calculationText.text.toString()
        userExp = userExp.replace("÷", "/")
        userExp = userExp.replace("×", "*")
        val exp = Expression(userExp)
        val result = exp.calculate().toString()
        binding.calculationText.setText(result)
        binding.calculationText.setSelection(result.length)
    }
    private fun dotBTN() { updateDisplay(".") }
    private fun plusMinusBTN() { updateDisplay("−") }
    private fun eraseBTN() {
        val cursorPos = binding.calculationText.selectionStart
        val textLength = binding.calculationText.length()
        if (cursorPos != 0 && textLength != 0) {
            val selection: SpannableStringBuilder =
                binding.calculationText.text as SpannableStringBuilder
            selection.replace(cursorPos - 1, cursorPos, "")
            binding.calculationText.text = selection
            binding.calculationText.setSelection(cursorPos - 1)
        }
    }
}