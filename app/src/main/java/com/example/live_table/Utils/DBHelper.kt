package com.example.live_table.Utils

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context?) : SQLiteOpenHelper(context, "Live_Table.db", null, 1) {

    override fun onCreate(p0: SQLiteDatabase?) {

        var quary =
            "CREATE TABLE live_table (id INTEGER PRIMARY KEY AUTOINCREMENT,tableNoTxt INTEGER, customer_name TEXT,number_of_people TEXT,booking_time TEXT)"
        p0!!.execSQL(quary)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun insertData(n1: String, n2: String, n3: String, n4 : String) {

        var db = writableDatabase

        var cv = ContentValues()
        cv.put("tableNoTxt",n1)
        cv.put("customer_name", n2)
        cv.put("number_of_people", n3)
        cv.put("booking_time",n4)

        var ret = db.insert("live_table", null, cv)
        println(ret)

    }

    @SuppressLint("Range")
    fun  readData(): ArrayList<ModelData> {

        var list = arrayListOf<ModelData>()

        var db = readableDatabase
        var quary = "SELECT * from live_table"

        var cursor = db.rawQuery(quary, null)

        if (cursor.moveToFirst()) {

            do {

                var id = cursor.getString(cursor.getColumnIndex("id"))
                var tableNoTxt = cursor.getString(cursor.getColumnIndex("tableNoTxt"))
                var customer_name = cursor.getString(cursor.getColumnIndex("customer_name"))
                var number_of_people = cursor.getString(cursor.getColumnIndex("number_of_people"))
                var booking_time = cursor.getString(cursor.getColumnIndex("booking_time"))

                var l1 = ModelData(id,tableNoTxt, customer_name, number_of_people,booking_time)
                list.add(l1)

            } while (cursor.moveToNext())

        }

        return list

    }

    fun deleteData(id: String) {

        var db = writableDatabase

        db.delete("live_table", "id = $id", null)

    }

    fun updateData(id : String, customer_name : String, number_of_people : String) {

        var db = writableDatabase

        var cv = ContentValues()

        cv.put("customer_name", customer_name)
        cv.put("number_of_people", number_of_people)

        db.update("live_table",cv,"id = $id",null)
    }
}