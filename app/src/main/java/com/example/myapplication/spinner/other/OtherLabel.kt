package com.example.myapplication.spinner.other

import android.annotation.SuppressLint
import com.example.myapplication.*

data class OtherLabel (val image: Int, val name: String)
private val applicationContext = GlobalApplication.getAppContext()
@SuppressLint("StaticFieldLeak")
val tinyDB: TinyDB = TinyDB(applicationContext)
    object OtherCategories {
        var list: ArrayList<OtherLabel>? = null
            get() {
                if (field != null)
                    return field
                field = ArrayList()
                for (i in tinyDB.getListInt("otherCategoriesImage").indices) {

                    val imageId = tinyDB.getListInt("otherCategoriesImage")[i]
                    val categoryName = tinyDB.getListString("otherCategoriesString")[i]

                    val category = OtherLabel(imageId, categoryName)
                    field!!.add(category)
                }
                return field
            }
    }
