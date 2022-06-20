package com.example.live_table.fragmentAdapter

import android.app.Dialog
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.live_table.R
import com.example.live_table.Utils.DBHelper
import com.example.live_table.Utils.ModelData
import com.example.live_table.fragments.Table_Fragment.Companion.binding_table
import java.util.*
import kotlin.collections.ArrayList
import android.app.AlertDialog as AlertDialog1

class Table_Fragment_Adapter(val factivity: FragmentActivity?, val l1: ArrayList<ModelData>) :
    RecyclerView.Adapter<Table_Fragment_Adapter.ViewData>() {

    companion object {

        lateinit var txt: TextView

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
        var view =
            LayoutInflater.from(factivity).inflate(R.layout.table_details_item, parent, false)
        return ViewData(view)
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        holder.id.text = l1[position].id
        holder.tableNoTxt.text = l1[position].tableNoTxt
        holder.customer_name_txt.text = l1[position].customer_name
        holder.num_people_txt.text = l1[position].number_of_people
        holder.time_txt.text = l1[position].booking_time

        holder.editImageBtn.setOnClickListener {

            updatedeletedialog(position)

        }

        holder.orderInfo.setOnClickListener {

            // withMultiChoiceList()
            orderInfodialog()

        }


    }

    override fun getItemCount(): Int {
        return l1.size
    }

    class ViewData(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var id = itemView.findViewById<TextView>(R.id.id)
        var tableNoTxt = itemView.findViewById<TextView>(R.id.tableNoTxt)
        var customer_name_txt = itemView.findViewById<TextView>(R.id.customer_name_txt)
        var num_people_txt = itemView.findViewById<TextView>(R.id.num_people_txt)
        var editImageBtn = itemView.findViewById<ImageView>(R.id.editImageBtn)
        var time_txt = itemView.findViewById<TextView>(R.id.time_txt)
        var orderInfo = itemView.findViewById<TextView>(R.id.orderInfo)


    }

    fun updatedeletedialog(position: Int) {

        var dialog = Dialog(factivity!!)
        dialog.setContentView(R.layout.update_delete_dialog)
        dialog.show()


        var update = dialog.findViewById<Button>(R.id.update_btn)
        var delete = dialog.findViewById<Button>(R.id.delete_btn)


        update.setOnClickListener {

            var update_dialog = Dialog(factivity)
            update_dialog.setContentView(R.layout.update_dialog)
            update_dialog.show()

            var tNo = update_dialog.findViewById<TextView>(R.id.tNo)
            var cName = update_dialog.findViewById<EditText>(R.id.cName)
            var noPeople = update_dialog.findViewById<EditText>(R.id.noPeople)
            var dialog_update_btn = update_dialog.findViewById<Button>(R.id.dialog_update_btn)

            tNo.setText("" + l1[position].tableNoTxt)
            cName.setText("" + l1[position].customer_name)
            noPeople.setText("" + l1[position].number_of_people)


            dialog_update_btn.setOnClickListener {


                DBHelper(factivity).updateData(
                    l1[position].id,
                    cName.text.toString(),
                    noPeople.text.toString()
                )
                var l1 = DBHelper(factivity).readData()
                setupRecyclerView(l1)

                Toast.makeText(factivity, "Item Updated Successfully", Toast.LENGTH_LONG).show()

                update_dialog.dismiss()
                dialog.dismiss()
            }
        }

        delete.setOnClickListener {

            DBHelper(factivity).deleteData(l1[position].id)
            var l1 = DBHelper(factivity).readData()
            setupRecyclerView(l1)

            Toast.makeText(factivity, "Item Deleted Successfully", Toast.LENGTH_LONG).show()

            dialog.dismiss()
        }


    }

    fun orderInfodialog() {

        val builder = AlertDialog.Builder(factivity!!)
        // String array for alert dialog multi choice items
        val colorsArray = arrayOf("Black", "Orange", "Green", "Yellow", "White", "Purple")
        // Boolean array for initial selected items
        val checkedColorsArray = booleanArrayOf(
            true, // Black checked
            false, // Orange
            false, // Green
            true, // Yellow checked
            false, // White
            false  //Purple
        )
        // Convert the color array to list
        val colorsList = Arrays.asList(*colorsArray)
        //setTitle
        builder.setTitle("Select colors")
        //set multichoice
        builder.setMultiChoiceItems(colorsArray, checkedColorsArray) { dialog, which, isChecked ->
            // Update the current focused item's checked status
            checkedColorsArray[which] = isChecked
            // Get the current focused item
            val currentItem = colorsList[which]
            // Notify the current action
            Toast.makeText(factivity, currentItem + " " , Toast.LENGTH_SHORT).show()
        }
        // Set the positive/yes button click listener
        builder.setPositiveButton("OK") { dialog, which ->
            // Do something when click positive button


        }
        // Set the neutral/cancel button click listener
        builder.setNeutralButton("Cancel") { dialog, which ->
            // Do something when click the neutral button
        }
        val dialog = builder.create()
        // Display the alert dialog on interface
        dialog.show()


//        var itemName = arrayOf("Paneer Tikka", "Aloo tikki", "Onion Pakoda", "Dahi Vada", "Pani Puri")
//        var check = arrayOf(false, false, false, false, false);
//
//        var alertDialog = AlertDialog.Builder(factivity!!)
//
//            alertDialog.setMultiChoiceItems(itemName,null){
//
//                    dialog, which, isChecked ->
//
//
//                val currentItem = orderInfodialog()[which]
//                // Notify the current action
//                Toast.makeText(this, currentItem + " " , Toast.LENGTH_SHORT).show()
//            }
//
//            }
//        var orderDialog = Dialog(factivity!!)
//        orderDialog.setContentView(R.layout.order_dialog)
//        orderDialog.show()
//
//        var orderRecyclerView = orderDialog.findViewById<RecyclerView>(R.id.orderRecyclerView)
//
//        var itemName = arrayOf("Paneer Tikka","Aloo tikki","Onion Pakoda","Dahi Vada","Pani Puri")
//        var itemPrice = arrayOf("100","150","200","250","300")
//
//        var orderadapter = orderAdapter(factivity,itemName,itemPrice)
//        var layoutManager = LinearLayoutManager(factivity)
//        orderRecyclerView.adapter = orderadapter
//        orderRecyclerView.layoutManager = layoutManager

    }

    fun setupRecyclerView(l1: ArrayList<ModelData>) {

        var adapter = Table_Fragment_Adapter(factivity, l1)
        var layoutManager = LinearLayoutManager(factivity)
        binding_table.seedataRvview.adapter = adapter
        binding_table.seedataRvview.layoutManager = layoutManager

    }


    fun withMultiChoiceList() {

        val items = arrayOf("Microsoft", "Apple", "Amazon", "Google")
        val selectedList = ArrayList<Int>()
        val builder = AlertDialog.Builder(factivity!!)

        builder.setTitle("This is list choice dialog box")
        builder.setMultiChoiceItems(items, null) { dialog, which, isChecked ->
            if (isChecked) {
                selectedList.add(which)
                Toast.makeText(factivity, "", Toast.LENGTH_LONG).show()
            } else if (selectedList.contains(which)) {
                selectedList.remove(Integer.valueOf(which))
            }
        }

        builder.show()

    }
}
