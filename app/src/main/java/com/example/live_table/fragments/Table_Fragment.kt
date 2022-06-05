package com.example.live_table.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.live_table.Utils.DBHelper
import com.example.live_table.Utils.ModelData
import com.example.live_table.databinding.FragmentTableBinding

class Table_Fragment : Fragment() {

    companion object {
        lateinit var binding_table: FragmentTableBinding

    }

    var list1 = ArrayList<ModelData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding_table = FragmentTableBinding.inflate(layoutInflater)

        var db = DBHelper(activity)

        list1 = db.readData()

        setupRecyclerView(list1)

        return binding_table.root
    }

    fun setupRecyclerView(l1: ArrayList<ModelData>) {

        var adapter = Table_Fragment_Adapter(activity, l1)
        var layoutManager = LinearLayoutManager(activity)
        binding_table.seedataRvview.adapter = adapter
        binding_table.seedataRvview.layoutManager = layoutManager

    }


}
