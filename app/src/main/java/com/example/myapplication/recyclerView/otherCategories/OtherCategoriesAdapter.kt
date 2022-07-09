package com.example.myapplication.recyclerView.otherCategories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.database.otherCategories.OtherCategoriesData
import com.example.myapplication.fragments.OtherInformationDirections

class OtherCategoriesAdapter(private val navController: NavController) : RecyclerView.Adapter<OtherCategoriesAdapter.ItemViewHolder>() {

    private var dataSet = emptyList<OtherCategoriesData>()

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var categoryIcon: ImageView = view.findViewById(R.id.category_icon)
        var categoryName: TextView = view.findViewById(R.id.category_name2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.other_categories_recyclerview_design, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataSet[position]
        holder.categoryIcon.setImageResource(item.imageId)
        holder.categoryName.text = item.text
        holder.itemView.setOnClickListener {
            val text = holder.categoryName.text.toString()
            val direction = OtherInformationDirections.actionOtherInformationToInformation(text)
            navController.navigate(direction)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun setData(category: List<OtherCategoriesData>) {
        this.dataSet = category.sortedByDescending { it.id }
        notifyDataSetChanged()
    }
}