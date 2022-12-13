package com.example.expensemanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton

class CategoryActivity : AppCompatActivity() {

    lateinit var edtCategory: EditText
    lateinit var btnAdd: AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        initView()

    }

    private fun initView() {

        val categoryDB =CategoryDB(this)
        edtCategory = findViewById(R.id.edtCategory)
        btnAdd = findViewById(R.id.btnAdd)


        btnAdd.setOnClickListener {

            val category = edtCategory.text.toString()
            if(category.isEmpty())
            {
                edtCategory.setError("please Enter Expense Category")
            }
            else
            {

                categoryDB.insertCategory(category)
                val i = Intent(this,MainActivity::class.java)
                startActivity(i)
                finish()
            }
        }



    }
}