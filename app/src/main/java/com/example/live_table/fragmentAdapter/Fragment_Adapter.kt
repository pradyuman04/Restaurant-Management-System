package com.example.live_table.fragmentAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.live_table.Screen.HomeScreenActivity.View.Home_Screen
import com.example.live_table.fragments.Home_Fragment
import com.example.live_table.fragments.Menu_Fragment
import com.example.live_table.fragments.Table_Fragment

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