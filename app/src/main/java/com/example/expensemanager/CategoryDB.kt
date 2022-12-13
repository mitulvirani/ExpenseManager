package com.example.expensemanager

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class CategoryDB(context: Context) : SQLiteOpenHelper(context,"CategoryDB.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var sql= "Create table CategoryTb(CategoryID integer primary key autoincrement,Category text)"
        db?.execSQL(sql)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun insertCategory(category: String) {

        val db = writableDatabase
        val c = ContentValues()
        c.put("Category", category)
        var result = db.insert("CategoryTb", null, c)
        Log.e("TAG", "dataInsert: result $result")


    }

    fun categoryDisplay(): ArrayList<CategoryModelClass> {

        val list: ArrayList<CategoryModelClass> = ArrayList()

        val db = readableDatabase
        val sql = "select * from CategoryTb"
        val cursor = db.rawQuery(sql, null)

        if (cursor.count > 0) {

            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(0)
                    val category = cursor.getString(1)



                    val categoryModelClass = CategoryModelClass(id, category)
                    list.add(categoryModelClass)

                    Log.e("TAG", "dataDisplay: " + id + "" + category )


                } while (cursor.moveToNext())
                return list
            }
        } else {
            Log.e("TAG", "categoryDisplay: " + "No Data Found")
        }
        return list
    }
}