package com.example.live_table.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.live_table.Screen.HomeScreenActivity.View.Home_Screen

class Fragment_Adapter(home_screen: Home_Screen, supportFragmentManager: FragmentManager) :
    FragmentPagerAdapter(supportFragmentManager) {

    override fun getCount(): Int {

        return  3;

    }

    override fun getItem(position: Int): Fragment {

        return  when (position){

            0 -> Home_Fragment()
            1 -> Menu_Fragment()

            else -> Table_Fragment()

        }

    }
}