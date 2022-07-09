package com.example.myapplication.recyclerView.details


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.database.expenses.Expense
import java.util.Collections.emptyList
class ExpensesDate : RecyclerView.Adapter<ExpensesDate.ItemViewHolder>() {

    private var dataSet = emptyList<Expense>()

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var categoryImage: ImageView = view.findViewById(R.id.category_ic)
        var textDate: TextView = view.findViewById(R.id.date)
        var textCategory: TextView = view.findViewById(R.id.category)
        val textTotal: TextView = view.findViewById(R.id.total)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.date_view, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataSet[position]
        holder.categoryImage.setImageResource(
            when (item.expenseType) {
                "Category" -> R.drawable.ic_category
                "Restaurant" -> R.drawable.ic_restaurant
                "Transport" -> R.drawable.ic_transport
                "Health" -> R.drawable.ic_health
                "Emergency" -> R.drawable.ic_emergency
                "Travel" -> R.drawable.ic_travel
                "Family" -> R.drawable.ic_family
                "Entertainment" -> R.drawable.ic_entertainment
                "Gifts" -> R.drawable.ic_gift
                "Shopping" -> R.drawable.ic_shopping
                else -> R.drawable.ic_other
            }
        )
        holder.textDate.text = item.expenseDate
        holder.textCategory.text = item.expenseType
        holder.textTotal.text = item.expenseCost.toString()
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun setData(expense: List<Expense>) {
        val expenseSorted = expense.sortedByDescending { it.id }
        this.dataSet = expenseSorted.sortedByDescending { it.expenseDate }
        notifyDataSetChanged()
    }
}