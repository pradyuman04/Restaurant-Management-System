package com.example.live_table.utils

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

        var quary2 = "CREATE TABLE table_info(id INTEGER PRIMARY KEY AUTOINCREMENT,tableNoTxt1 INTEGER,status INTEGER)"

        p0!!.execSQL(quary2)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun insertTableData(t1: String,t2:String){

        var db2 = writableDatabase

        var cv2 = ContentValues()
        cv2.put("tableNoTxt1", t1)
        cv2.put("status", t2)

        var ret2 = db2.insert("table_info", null, cv2)
        println(ret2)


    }

    @SuppressLint("Range")
    fun  readTableData(): ArrayList<viewModelData> {

        var list2 = arrayListOf<viewModelData>()

        var db = readableDatabase
        var quary = "SELECT * from table_info"

        var cursor = db.rawQuery(quary, null)

        if (cursor.moveToFirst()) {

            do {

                var id = cursor.getString(cursor.getColumnIndex("id"))
                var tableNoTxt1 = cursor.getString(cursor.getColumnIndex("tableNoTxt1"))
                var staus = cursor.getString(cursor.getColumnIndex("status"))


                var l2 = viewModelData(id,tableNoTxt1, staus)
                list2.add(l2)

            } while (cursor.moveToNext())

        }

        return list2

    }


    fun checkData(): Boolean {
        var db=readableDatabase
        var sql="select * from table_info"
        var cursor=db.rawQuery(sql,null)
        return cursor.count > 0
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

    fun updateTableData(id: String, status: Int){

        var db  = writableDatabase

        var cv2 = ContentValues()

        cv2.put("status",status)

        db.update("table_info",cv2,"id = $id",null)

    }
}