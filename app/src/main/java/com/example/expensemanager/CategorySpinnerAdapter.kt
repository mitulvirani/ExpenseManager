package com.example.expensemanager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter

class CategorySpinnerAdapter(
    var context: Context,
    var list: ArrayList<CategoryModelClass>,
    var categoryItemClick: ((String) -> Unit)
) : BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var txtCategory: TextView
        var v: View = LayoutInflater.from(context).inflate(R.layout.category_list, null)
        txtCategory = v.findViewById(R.id.txtCategory)
        txtCategory.setText(list.get(p0).category)
        txtCategory.setOnClickListener {
            categoryItemClick.invoke(list.get(p0).category)
        }
        return v
    }
}