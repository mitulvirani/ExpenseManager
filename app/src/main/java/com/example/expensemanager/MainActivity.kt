package com.example.expensemanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    lateinit var cdvAddIncome: CardView
    lateinit var cdvAddExpense: CardView
    lateinit var cdvAddCategory: CardView
    lateinit var cdvViewCategory: CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

    }

    private fun initView() {

        cdvAddIncome = findViewById(R.id.cdvAddIncome)
        cdvAddExpense = findViewById(R.id.cdvAddExpense)
        cdvAddCategory = findViewById(R.id.cdvAddCategory)
        cdvViewCategory = findViewById(R.id.cdvViewCategory)


        cdvAddCategory.setOnClickListener {

            var i = Intent(this, CategoryActivity::class.java)
            startActivity(i)
        }
        cdvViewCategory.setOnClickListener{

            var i = Intent(this, ViewCategoryActivity::class.java)
            startActivity(i)

        }
        cdvAddIncome.setOnClickListener{

            var i = Intent(this, AddInccomeExpenseActivity::class.java)
            i.putExtra("type","Income")
            startActivity(i)

        }
        cdvAddExpense.setOnClickListener{

            var i = Intent(this, AddInccomeExpenseActivity::class.java)
            i.putExtra("type","Expense")

            startActivity(i)

        }
    }
}