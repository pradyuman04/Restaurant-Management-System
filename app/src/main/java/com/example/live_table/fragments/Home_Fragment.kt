package com.example.live_table.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.live_table.R
import com.example.live_table.Activity.HomeScreenActivity.View.Home_Screen.Companion.binding11
import com.example.live_table.Utils.DBHelper
import com.example.live_table.Utils.ModelData
import com.example.live_table.Utils.viewModelData
import com.example.live_table.databinding.FragmentHomeBinding
import com.example.live_table.fragmentAdapter.viewTableAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class Home_Fragment : Fragment() {


    lateinit var binding: FragmentHomeBinding
    val list = mutableListOf<CarouselItem>()
    var list1 = ArrayList<ModelData>()
    var list2 = ArrayList<viewModelData>()
    var i = 10

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.tableDetails.setOnClickListener {

            binding11.viewPager.currentItem = 2

        }

        imageslider()


        var db = DBHelper(activity)





        setupTableRecyclerView()


        val simpleDateFormat = SimpleDateFormat("HH:mm:ss")
        val currentDateAndTime: String = simpleDateFormat.format(Date())

        binding.tableDetailsTxt.setOnClickListener {

            binding11.viewPager.currentItem = 2
        }

        binding.bookBtn.setOnClickListener {



                 if (binding.customerNameEdt.text.isNullOrEmpty()) {

                    binding.customerNameEdt.error = "Please Enter Customer Name"

                } else if (binding.peopleEdt.text.isNullOrEmpty()) {

                    binding.peopleEdt.error = "Please Enter Number of People"

                } else {

                    db.insertData(
                        binding.tableNoEdt.text.toString(),
                        binding.customerNameEdt.text.toString(),
                        binding.peopleEdt.text.toString(),
                        currentDateAndTime

                        )


                    binding.tableNoEdt.setText(null)
                    binding.customerNameEdt.setText(null)
                    binding.peopleEdt.setText(null)

                    binding11.viewPager.currentItem = 2

                    Toast.makeText(activity, "Table Booked Successfully", Toast.LENGTH_LONG).show()

                }

            }



            list1 = db.readData()



        return binding.root
    }


    fun bottomSheet(i: Int) {


        var db = DBHelper(activity)



        var dialog1 = activity?.let { BottomSheetDialog(it) }
        dialog1?.setContentView(R.layout.get_data_bottomsheet)

        val simpleDateFormat = SimpleDateFormat("HH:mm:ss")
        val currentDateAndTime: String = simpleDateFormat.format(Date())


        var tableNoTxt = dialog1?.findViewById<TextView>(R.id.table_no_txt)
        var customer_name = dialog1?.findViewById<EditText>(R.id.customer_name_edt1)
        var number_of_people = dialog1?.findViewById<EditText>(R.id.people_edt1)

        var book_btn1 = dialog1?.findViewById<Button>(R.id.book_btn1)

        tableNoTxt!!.setText(i.toString())

        book_btn1!!.setOnClickListener {

            if (customer_name!!.text.isNullOrEmpty()) {

                customer_name.error = "Please Enter Customer Name"

            } else if (number_of_people!!.text.isNullOrEmpty()) {
                number_of_people.error = "Please Enter Number of People"

            } else {



                db.insertData(
                    tableNoTxt.text.toString(),
                    customer_name.text.toString(),
                    number_of_people.text.toString(),
                    currentDateAndTime,

                )



                customer_name.setText(null)
                number_of_people.setText(null)

                binding11.viewPager.currentItem = 2


                    Toast.makeText(activity, "Table Booked Successfully", Toast.LENGTH_LONG).show()



                dialog1!!.dismiss()

            }

            list1 = db.readData()



        }
            dialog1?.show()

    }

    fun imageslider() {

        list.add(

            CarouselItem(

                imageDrawable = R.drawable.image1,

                )

        )
        list.add(

            CarouselItem(

                imageDrawable = R.drawable.image2,
            )

        )
        list.add(

            CarouselItem(

                imageDrawable = R.drawable.image6,
            )

        )
        list.add(

            CarouselItem(

                imageDrawable = R.drawable.image3,
            )

        )
        list.add(

            CarouselItem(

                imageDrawable = R.drawable.image4,
            )

        )
        list.add(

            CarouselItem(

                imageDrawable = R.drawable.image6,
            )

        )

        binding.imgSlider.setData(list)

//       binding.timeSpinner = ArrayAdapter(activity?, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(R.array.Languages))

    }

    fun setupTableRecyclerView(){

        var db = DBHelper(activity)
      list2 =  db.readTableData()

        var adapter = viewTableAdapter(activity,list2)
        var layoutManager = GridLayoutManager(activity,3)
        binding.tableRecyclerView.adapter = adapter
        binding.tableRecyclerView.layoutManager = layoutManager



    }


    /*fun setupSpinner() {

        val personNames = arrayOf("Rahul", "Jack", "Rajeev", "Aryan", "Rashmi", "Jaspreet", "Akbar")
        val spinner = binding.timeSpinner
        val arrayAdapter  =
            activity?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, personNames) }
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>,view: View,position: Int,id: Long)

            {}

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Code to perform some action when nothing is selected
            }

        }

    }*/
}