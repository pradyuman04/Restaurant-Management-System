package com.example.live_table.activity.historyActivity.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.live_table.activity.historyActivity.controller.historyAdapter
import com.example.live_table.databinding.HistoryActivityBinding
import com.example.live_table.utils.DBHelper
import com.example.live_table.utils.ModelData

class historyActivity : AppCompatActivity() {

    lateinit var binding: HistoryActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = HistoryActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var l1 = DBHelper(this).readHistoryData()
        setHistory(l1)

        binding.backImage.setOnClickListener {

            onBackPressed()
        }
    }

    fun setHistory(list: ArrayList<ModelData>){

        var adapter = historyAdapter(this,list)
        var layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = layoutManager

    }
}