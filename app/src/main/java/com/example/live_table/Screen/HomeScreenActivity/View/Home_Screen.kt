package com.example.live_table.Screen.HomeScreenActivity.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.live_table.fragments.Fragment_Adapter
import com.example.live_table.databinding.HomeScreenBinding
import com.google.android.material.tabs.TabLayout

class Home_Screen : AppCompatActivity() {

    companion object
    {
        lateinit var binding11: HomeScreenBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding11 = HomeScreenBinding.inflate(layoutInflater)
        setContentView(binding11.root)

        tab_layout()

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
                binding11.viewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }
}