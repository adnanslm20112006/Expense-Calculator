package com.example.myapplication.spinner.expense

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.myapplication.R
import kotlinx.android.synthetic.main.custom_spinner.view.*

class LabelArrayAdapter(context: Context, LabelList: List<Label>): ArrayAdapter<Label>(context, 0, LabelList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, parent)
    }
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, parent)
    }
    private fun initView(position: Int, parent: ViewGroup): View {
        val label = getItem(position)
        val view = LayoutInflater.from(context).inflate(R.layout.custom_spinner, parent, false)
        view.category_ic.setImageResource(label!!.image)
        view.category_text.text = label.name
        return view
    }
}