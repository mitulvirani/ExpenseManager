package com.example.expensemanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewCategoryActivity : AppCompatActivity() {

    lateinit var rcvViewCategory:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_category)


        initView()
    }

    private fun initView() {

        rcvViewCategory=findViewById(R.id.rcvViewCategory)

        val categoryDB =CategoryDB(this)

        val list=categoryDB.categoryDisplay()
        val categoryAdapter=CategoryAdapter(list)
        val manager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rcvViewCategory.layoutManager=manager
        rcvViewCategory.adapter=categoryAdapter
    }
}