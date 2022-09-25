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
import com.example.live_table.fragmentAdapter.menuFragmentAdapter


class menuFragment : Fragment() {

    lateinit var binding: FragmentMenuBinding

    var dish_name = arrayOf(
        "Dahi Puri",
        "Aloo tikki",
        "Onion Pakoda",
        "Dahi Vada",
        "Pani Puri",
        "Khaman Dhokla",
        "Veg Cutlet",
        "French Fries",
        "Bhel Puri",
        "Cheese Ball",
        "Medu Vada",
        "Kachori",
        "Gobi Manchurian",
        "Samosa",
        "Hara Bhara Kabab"
    )
    var dish_price = arrayOf(
        "150",
        "100",
        "120",
        "70",
        "50",
        "150",
        "40",
        "130",
        "80",
        "150",
        "60",
        "150",
        "140",
        "50",
        "80"
    )

    var dish_name2 = arrayOf(
        "Paneer Butter Masala",
        "Dal Palak",
        "Paneer Tikka Masala",
        "Aloo Matar",
        "Kadai Paneer",
        "Gujarati Dal Recipe",
        "Palak Paneer",
        "Aloo Gobi",
        "Kaju Masala",
        "Chana Masala",
        "Medu Vada",
        "Kachori",
        "Gobi Manchurian",
        "Samosa",
        "Hara Bhara Kabab"
    )
    var dish_price2 = arrayOf(
        "250",
        "150",
        "230",
        "150",
        "300",
        "350",
        "400",
        "450",
        "500",
        "550",
        "600",
        "650",
        "700",
        "750",
        "800"
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMenuBinding.inflate(layoutInflater)

        setupRecyclerView1()

        return binding.root

    }

    fun setupRecyclerView1() {

        var adapter = menuFragmentAdapter(activity, dish_name, dish_price)
        var layoutManager = LinearLayoutManager(activity)
        binding.menuRvView.adapter = adapter
        binding.menuRvView.layoutManager = layoutManager

        var adapter2 = menuFragmentAdapter(activity, dish_name2, dish_price2)
        var layoutManager2 = LinearLayoutManager(activity)
        binding.menuRvView2.adapter = adapter2
        binding.menuRvView2.layoutManager = layoutManager2

        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.menuRvView)
        snapHelper.attachToRecyclerView(binding.menuRvView2)

    }


}