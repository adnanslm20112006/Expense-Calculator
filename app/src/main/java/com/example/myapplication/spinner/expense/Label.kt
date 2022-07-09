package com.example.myapplication.spinner.expense

import com.example.myapplication.R

data class Label(val image: Int, val name: String)
    object Categories {

        private val images = intArrayOf(
            R.drawable.ic_category,
            R.drawable.ic_restaurant,
            R.drawable.ic_transport,
            R.drawable.ic_health,
            R.drawable.ic_emergency,
            R.drawable.ic_travel,
            R.drawable.ic_family,
            R.drawable.ic_entertainment,
            R.drawable.ic_gift,
            R.drawable.ic_shopping,
            R.drawable.ic_other
        )

        private val countries = arrayOf(
            "Category",
            "Restaurant",
            "Transport",
            "Health",
            "Emergency",
            "Travel",
            "Family",
            "Entertainment",
            "Gifts",
            "Shopping",
            "Other"
        )

        var list: ArrayList<Label>? = null
            get() {

                if (field != null)
                    return field

                field = ArrayList()
                for (i in images.indices) {

                    val imageId = images[i]
                    val countryName = countries[i]

                    val country = Label(imageId, countryName)
                    field!!.add(country)
                }

                return field
            }
    }