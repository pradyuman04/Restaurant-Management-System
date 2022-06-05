package com.example.live_table.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.live_table.R
import com.example.live_table.Screen.HomeScreenActivity.View.Home_Screen.Companion.binding11
import com.example.live_table.Utils.DBHelper
import com.example.live_table.Utils.ModelData
import com.example.live_table.databinding.FragmentHomeBinding
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem


class Home_Fragment : Fragment() {


    lateinit var binding: FragmentHomeBinding
    val list = mutableListOf<CarouselItem>()
    var list1 = ArrayList<ModelData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)


        var db = DBHelper(activity)

        imageslider()

        binding.tableDetailsTxt.setOnClickListener {

            binding11.viewPager.currentItem = 2
        }

        binding.bookBtn.setOnClickListener {

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
                )

                binding.tableNoEdt.setText(null)
                binding.customerNameEdt.setText(null)
                binding.peopleEdt.setText(null)

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

    }

}