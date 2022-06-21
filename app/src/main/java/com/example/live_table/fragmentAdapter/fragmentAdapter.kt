package com.example.live_table.fragmentAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.live_table.activity.homescreenActivity.View.homescreenActivity
import com.example.live_table.fragments.homeFragment
import com.example.live_table.fragments.menuFragment
import com.example.live_table.fragments.tableFragment

class fragmentAdapter(homescreenActivity: homescreenActivity, supportFragmentManager: FragmentManager) :
    FragmentPagerAdapter(supportFragmentManager) {

    override fun getCount(): Int {

        return  3;

    }

    override fun getItem(position: Int): Fragment {

        return  when (position){

            0 -> homeFragment()
            1 -> menuFragment()

            else -> tableFragment()

        }

    }
}