package com.example.live_table.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.live_table.R
import com.example.live_table.activity.homescreenActivity.View.homescreenActivity.Companion.binding11
import com.example.live_table.databinding.FragmentHomeBinding
import com.example.live_table.fragmentAdapter.homeFragmentAdapter
import com.example.live_table.utils.DBHelper
import com.example.live_table.utils.ModelData
import com.example.live_table.utils.viewModelData
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import java.text.SimpleDateFormat
import java.util.*


class homeFragment : Fragment() {


    lateinit var binding: FragmentHomeBinding
    val list = mutableListOf<CarouselItem>()
    var list1 = ArrayList<ModelData>()
    var list2 = ArrayList<viewModelData>()

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

        //var fullname: String? = getIntent().getStringExtra("n1")


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
                        currentDateAndTime)

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

    }

    fun setupTableRecyclerView(){

        var db = DBHelper(activity)
      list2 =  db.readTableData()

        var adapter = homeFragmentAdapter(activity,list2)
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