package com.example.myapplication.recyclerView.otherCategoryIcon

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.icons


class OtherCategoryIcon: RecyclerView.Adapter<OtherCategoryIcon.ItemViewHolder>() {
    val list = icons
    private lateinit var preferences: SharedPreferences
    private var lastViewHolder: ItemViewHolder? = null
    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val icon: (ImageView) = view.findViewById(R.id.icon)
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        preferences = holder.itemView.context.getSharedPreferences("User", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        val item = list[position]
        holder.icon.setImageResource(item)
        holder.icon.setOnClickListener {
            holder.icon.background = ResourcesCompat.getDrawable(holder.itemView.context.resources, R.color.selection_color, null)
            editor.putInt("selectedIcon", item)
            editor.apply()
            lastViewHolder?.let {
                it.icon.background = ResourcesCompat.getDrawable(holder.itemView.context.resources, R.color.white, null)
            }
            lastViewHolder = holder
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapter = LayoutInflater.from(parent.context).inflate(R.layout.icons_layout, parent, false)
        return ItemViewHolder(adapter)
    }
}