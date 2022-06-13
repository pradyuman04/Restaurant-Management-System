package com.example.live_table.fragmentAdapter

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.live_table.R
import com.example.live_table.Utils.DBHelper
import com.example.live_table.Utils.ModelData
import com.example.live_table.fragments.Table_Fragment.Companion.binding_table
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Table_Fragment_Adapter(val factivity: FragmentActivity?, val l1: ArrayList<ModelData>) :
    RecyclerView.Adapter<Table_Fragment_Adapter.ViewData>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
        var view = LayoutInflater.from(factivity).inflate(R.layout.item, parent, false)
        return ViewData(view)
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        holder.id.text = l1[position].id
        holder.tableNoTxt.text = l1[position].tableNoTxt
        holder.customer_name_txt.text = l1[position].customer_name
        holder.num_people_txt.text = l1[position].number_of_people
        holder.time_txt.text = l1[position].booking_time

        holder.editImageBtn.setOnClickListener {

            opendialog(position)

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


    }

    fun opendialog(position:Int) {

        var dialog = Dialog(factivity!!)
        dialog.setContentView(R.layout.dialog)
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

           tNo.setText(""+l1[position].tableNoTxt)
           cName.setText(""+l1[position].customer_name)
           noPeople.setText(""+l1[position].number_of_people)


           dialog_update_btn.setOnClickListener {


               DBHelper(factivity).updateData(l1[position].id,cName.text.toString(),noPeople.text.toString())
                var l1 = DBHelper(factivity).readData()
               setupRecyclerView(l1)

               Toast.makeText(factivity,"Item Updated Successfully",Toast.LENGTH_LONG).show()

               update_dialog.dismiss()
               dialog.dismiss()
           }
       }

        delete.setOnClickListener {

            DBHelper(factivity).deleteData(l1[position].id)
            var l1 = DBHelper(factivity).readData()
            setupRecyclerView(l1)

            Toast.makeText(factivity,"Item Deleted Successfully",Toast.LENGTH_LONG).show()

            dialog.dismiss()
        }


    }

    fun setupRecyclerView(l1:ArrayList<ModelData>){

        var adapter = Table_Fragment_Adapter(factivity,l1)
        var layoutManager = LinearLayoutManager(factivity)
        binding_table.seedataRvview.adapter = adapter
        binding_table.seedataRvview.layoutManager = layoutManager

    }
}