package com.example.live_table.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.live_table.databinding.FragmentMenuBinding


class Menu_Fragment : Fragment() {

    lateinit var binding: FragmentMenuBinding

    var dish_name = arrayOf("Paneer Tikka","Aloo tikki","Onion Pakoda","Dahi Vada","Pani Puri","Khaman Dhokla","Veg Cutlet","French Fries","Bhel Puri","Cheese Ball","Medu Vada","Kachori","Gobi Manchurian","Samosa","Hara Bhara Kabab")
    var dish_price = arrayOf("100","150","200","250","300","350","400","450","500","550","600","650","700","750","800")

        var dish_name2 = arrayOf("0Paneer Tikka","0Aloo tikki","Onion Pakoda","Dahi Vada","Pani Puri","Khaman Dhokla","Veg Cutlet","French Fries","Bhel Puri","Cheese Ball","Medu Vada","Kachori","Gobi Manchurian","Samosa","Hara Bhara Kabab")
        var dish_price2 = arrayOf("0100","0150","0200","250","300","350","400","450","500","550","600","650","700","750","800")



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMenuBinding.inflate(layoutInflater)

        setupRecyclerView1()

        return binding.root

    }

    fun setupRecyclerView1(){

        var adapter = Menu_Fragment_Adapter(activity,dish_name,dish_price)
        var layoutManager = LinearLayoutManager(activity)
        binding.menuRvView.adapter = adapter
        binding.menuRvView.layoutManager = layoutManager

        var adapter2 = Menu_Fragment_Adapter(activity,dish_name2,dish_price2)
        var layoutManager2 = LinearLayoutManager(activity)
        binding.menuRvView2.adapter = adapter2
        binding.menuRvView2.layoutManager = layoutManager2

        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.menuRvView)
        snapHelper.attachToRecyclerView(binding.menuRvView2)

    }


}