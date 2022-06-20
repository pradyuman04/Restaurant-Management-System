package com.example.live_table.Activity.HomeScreenActivity.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.live_table.Utils.DBHelper
import com.example.live_table.fragmentAdapter.Fragment_Adapter
import com.example.live_table.databinding.HomeScreenBinding
import com.google.android.material.tabs.TabLayout

class Home_Screen : AppCompatActivity() {
    var i = 1

    companion object
    {
        lateinit var binding11: HomeScreenBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding11 = HomeScreenBinding.inflate(layoutInflater)
        setContentView(binding11.root)

        tab_layout()

        var dbHelper2 =  DBHelper(this)

        /*while (i<=12){

            var dbHelper2 =  DBHelper(this)
            dbHelper2.insertTableData("$i","0")
            i++

        }*/

    }

    fun tab_layout(){


        binding11.tabLayout.addTab(binding11.tabLayout.newTab().setText("HOME"))
        binding11.tabLayout.addTab(binding11.tabLayout.newTab().setText("MENU"))
        binding11.tabLayout.addTab(binding11.tabLayout.newTab().setText("TABLE INFO"))

        var adapter = Fragment_Adapter(this,supportFragmentManager)
        binding11.viewPager.adapter = adapter

        binding11.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding11.tabLayout))

        binding11.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding11.viewPager.currentItem = tab?.position!!
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

}