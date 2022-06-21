package com.example.live_table.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.live_table.utils.DBHelper
import com.example.live_table.utils.ModelData
import com.example.live_table.utils.viewModelData
import com.example.live_table.databinding.FragmentTableBinding
import com.example.live_table.fragmentAdapter.tableFragmentAdapter

class tableFragment : Fragment() {

    companion object {
        lateinit var binding_table: FragmentTableBinding

    }

    var list1 = ArrayList<ModelData>()
    var list2 = ArrayList<viewModelData>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding_table = FragmentTableBinding.inflate(layoutInflater)

        var db = DBHelper(activity)

        list1 = db.readData()
        list2 = db.readTableData()

        setupRecyclerView(list1,list2)

        return binding_table.root
    }

    fun setupRecyclerView(l1: ArrayList<ModelData>,l2:ArrayList<viewModelData>) {

        var adapter = tableFragmentAdapter(activity, l1,l2)
        var layoutManager = LinearLayoutManager(activity)
        binding_table.seedataRvview.adapter = adapter
        binding_table.seedataRvview.layoutManager = layoutManager

    }


}
