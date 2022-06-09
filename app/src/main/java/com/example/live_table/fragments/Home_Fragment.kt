package com.example.live_table.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.live_table.R
import com.example.live_table.Screen.HomeScreenActivity.View.Home_Screen.Companion.binding11
import com.example.live_table.Utils.DBHelper
import com.example.live_table.Utils.ModelData
import com.example.live_table.databinding.FragmentHomeBinding
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class Home_Fragment : Fragment() {


    lateinit var binding: FragmentHomeBinding
    val list = mutableListOf<CarouselItem>()
    var list1 = ArrayList<ModelData>()
    var i = 10

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.noOfTableTxt.text = i.toString()



        imageslider()


        var db = DBHelper(activity)


        val simpleDateFormat = SimpleDateFormat("HH:mm:ss")
        val currentDateAndTime: String = simpleDateFormat.format(Date())

        binding.tableDetailsTxt.setOnClickListener {

            binding11.viewPager.currentItem = 2
        }

        binding.bookBtn.setOnClickListener {

            if(i>=1)
            {

                if (binding.tableNoEdt.text.isNullOrEmpty()) {

                    binding.tableNoEdt?.error = "Please Enter Table No."

                } else if (binding.customerNameEdt.text.isNullOrEmpty()) {

                    binding.customerNameEdt?.error = "Please Enter Customer Name"

                } else if (binding.peopleEdt.text.isNullOrEmpty()) {

                    binding.peopleEdt?.error = "Please Enter Number of People"

                } else {

                    db.insertData(
                        binding.tableNoEdt.text.toString(),
                        binding.customerNameEdt.text.toString(),
                        binding.peopleEdt.text.toString(),
                        currentDateAndTime


                    )

                }


                i = i - 1
                binding.noOfTableTxt.text = i.toString()


                binding.tableNoEdt.setText(null)
                binding.customerNameEdt.setText(null)
                binding.peopleEdt.setText(null)

                binding11.viewPager.currentItem = 2

                Toast.makeText(activity, "Table Booked Successfully", Toast.LENGTH_LONG).show()
            }



            list1 = db.readData()

        }

        return binding.root
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