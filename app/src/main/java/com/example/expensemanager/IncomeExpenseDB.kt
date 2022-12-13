package com.example.expensemanager

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import androidx.core.content.contentValuesOf

class IncomeExpenseDB(val context: Context) : SQLiteOpenHelper(context,"IncomeExpenseDB.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var sql = "Create table IncomeExpenseTb(IncomeExpenseID integer primary key autoincrement,Amount text,Category Text,PaymentMethod text,Description text,Date text,Type int)"
        db?.execSQL(sql)
    }


    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun insertIncomeExpense(
        incomeExpense: String,
        category: String?,
        paymentMethod: String,
        description: String,
        date: String,
        type: String
    )
    {
        val db = writableDatabase
        val c = contentValuesOf()

        c.put("Amount",incomeExpense)
        c.put("Category",category)
        c.put("PaymentMethod",paymentMethod)
        c.put("Description",description)
        c.put("Date",date)
        c.put("Type",type.toString())


        val result =db.insert("IncomeExpenseTb",null,c)
        Log.e("TAG", "insertIncomeExpense:=="+result )
    }

    fun displayIncomeExpense(): ArrayList<IncomeExpenseModelClass> {
        var list : ArrayList<IncomeExpenseModelClass> = ArrayList()
        val db = writableDatabase
        var sql="Select * from IncomeExpenseTb"
        var cursor = db.rawQuery(sql,null)
        if (cursor.count > 0)
        {
            if (cursor.moveToFirst())
            {
                do {
                    var Id = cursor.getInt(0)
                    var Amount = cursor.getInt(1)
                    var Category = cursor.getString(2)
                    var Description = cursor.getString(3)
                    var Date = cursor.getString(4)
                    var Type = cursor.getInt(5)
                    var modelClass = IncomeExpenseModelClass(Id,Amount,Category,Description,Date,Type)
                    list.add(modelClass)
                }while (cursor.moveToNext())
                return list
            }
        }
        else
        {
            Toast.makeText(context, "Data Not Found", Toast.LENGTH_SHORT).show()
        }
        return list
    }
}