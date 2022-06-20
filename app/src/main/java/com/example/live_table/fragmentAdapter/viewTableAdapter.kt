package com.example.live_table.fragmentAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.live_table.Activity.HomeScreenActivity.View.Home_Screen
import com.example.live_table.R
import com.example.live_table.Utils.DBHelper
import com.example.live_table.Utils.viewModelData
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class viewTableAdapter(val activity: FragmentActivity?, val list2: ArrayList<viewModelData>) :
    RecyclerView.Adapter<viewTableAdapter.ViewD>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewD {
        var view = LayoutInflater.from(activity).inflate(R.layout.view_table, parent, false)
        return ViewD(view)
    }

    override fun onBindViewHolder(holder: ViewD, position: Int) {
        holder.tableNoTxt.text = list2[position].tableNoTxt1

        holder.rowOne.setOnClickListener {

            bottomSheet(list2[position].tableNoTxt1)


        }
    }

    override fun getItemCount(): Int {

        return list2.size

    }


    class ViewD(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tableNoTxt = itemView.findViewById<TextView>(R.id.tableNoTxt)
        var rowOne = itemView.findViewById<RelativeLayout>(R.id.rowOne)

    }

    fun bottomSheet(tableNoTxt1: String) {


        var db = DBHelper(activity)


        var dialog1 = activity?.let { BottomSheetDialog(it) }
        dialog1?.setContentView(R.layout.get_data_bottomsheet)

        val simpleDateFormat = SimpleDateFormat("HH:mm:ss")
        val currentDateAndTime: String = simpleDateFormat.format(Date())


        var tableNoTxt = dialog1?.findViewById<TextView>(R.id.table_no_txt)
        var customer_name = dialog1?.findViewById<EditText>(R.id.customer_name_edt1)
        var number_of_people = dialog1?.findViewById<EditText>(R.id.people_edt1)

        var book_btn1 = dialog1?.findViewById<Button>(R.id.book_btn1)

        tableNoTxt?.text = tableNoTxt1

        book_btn1?.setOnClickListener {

            if (customer_name!!.text.isNullOrEmpty()) {

                customer_name.error = "Please Enter Customer Name"

            } else if (number_of_people!!.text.isNullOrEmpty()) {
                number_of_people.error = "Please Enter Number of People"

            } else {


                db.insertData(
                    tableNoTxt?.text.toString(),
                    customer_name.text.toString(),
                    number_of_people.text.toString(),
                    currentDateAndTime,

                    )

                customer_name.setText(null)
                number_of_people.setText(null)

                Home_Screen.binding11.viewPager.currentItem = 2


                Toast.makeText(activity, "Table Booked Successfully", Toast.LENGTH_LONG).show()



                dialog1!!.dismiss()

            }


        }
        dialog1?.show()

    }

}