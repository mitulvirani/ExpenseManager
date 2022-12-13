package com.example.expensemanager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(val list: ArrayList<CategoryModelClass>) : RecyclerView.Adapter<CategoryAdapter.myViewHolder>() {

    lateinit var  context : Context

    class myViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val txtName: TextView = v.findViewById(R.id.txtCategory)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        context=parent.context
        var v = LayoutInflater.from(parent.context).inflate(R.layout.category_list, parent, false)

        return myViewHolder(v)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {

        holder.txtName.setText(list.get(position).category)

    }

    override fun getItemCount(): Int {
        return list.size
    }
}