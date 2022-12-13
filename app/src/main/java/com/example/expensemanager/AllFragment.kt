package com.example.expensemanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class AllFragment : Fragment() {
   lateinit var v : View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_all, container, false)

        initView()
        return v
    }

    private fun initView() {
        var db=IncomeExpenseDB(requireActivity())
        var list=db.displayIncomeExpense()
        var adapter = AllAdapter(list)
    }


}