package com.example.expensemanager

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AllAdapter(var list: ArrayList<IncomeExpenseModelClass>) :
    RecyclerView.Adapter<AllAdapter.myViewHolder>() {
    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {

    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return list.size
    }
}