package com.example.myapplication.spinner.other

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.res.ResourcesCompat.getDrawable
import androidx.core.graphics.drawable.toBitmap
import com.example.myapplication.R
import kotlinx.android.synthetic.main.custom_spinner.view.*


class OtherLabelArrayAdapter(context: Context, categoriesList: List<OtherLabel>): ArrayAdapter<OtherLabel>(context, 0,categoriesList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, parent)
    }
    private fun initView(position: Int, parent: ViewGroup): View {
        val label = getItem(position)
        val view = LayoutInflater.from(context).inflate(R.layout.custom_spinner, parent, false)
        val bd = getDrawable(context.resources, label!!.image, null)?.toBitmap()
        view.category_ic.setImageBitmap(Bitmap.createScaledBitmap(bd!!, 70, 70, false))
        view.category_text.text = label.name
        return view
    }
}