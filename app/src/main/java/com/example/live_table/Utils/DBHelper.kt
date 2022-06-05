package com.example.live_table.Utils

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context?) : SQLiteOpenHelper(context, "Live_Table.db", null, 1) {

    override fun onCreate(p0: SQLiteDatabase?) {

        var quary =
            "CREATE TABLE live_table (id INTEGER PRIMARY KEY AUTOINCREMENT, table_no TEXT,customer_name TEXT,number_of_people TEXT)"
        p0!!.execSQL(quary)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun insertData(n1: String, n2: String, n3: String) {

        var db = writableDatabase

        var cv = ContentValues()
        cv.put("table_no", n1)
        cv.put("customer_name", n2)
        cv.put("number_of_people", n3)

        var ret = db.insert("live_table", null, cv)
        println(ret)

    }

    @SuppressLint("Range")
    fun readData(): ArrayList<ModelData> {

        var list = arrayListOf<ModelData>()

        var db = readableDatabase
        var quary = "SELECT * from live_table"

        var cursor = db.rawQuery(quary, null)

        if (cursor.moveToFirst()) {

            do {

                var id = cursor.getString(cursor.getColumnIndex("id"))
                var table_no = cursor.getString(cursor.getColumnIndex("table_no"))
                var customer_name = cursor.getString(cursor.getColumnIndex("customer_name"))
                var number_of_people = cursor.getString(cursor.getColumnIndex("number_of_people"))

                var l1 = ModelData(id, table_no, customer_name, number_of_people)
                list.add(l1)

            } while (cursor.moveToNext())

        }

        return list

    }

    fun deleteData(id: String) {

        var db = writableDatabase

        db.delete("live_table", "id = $id", null)

    }

    fun updateData(id : String, table_no : String, customer_name : String, number_of_people : String) {

        var db = writableDatabase

        var cv = ContentValues()

        cv.put("table_no", table_no)
        cv.put("customer_name", customer_name)
        cv.put("number_of_people", number_of_people)

        db.update("live_table",cv,"id = $id",null)
    }
}