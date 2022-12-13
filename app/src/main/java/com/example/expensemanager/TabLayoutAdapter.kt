package com.example.expensemanager

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabLayoutAdapter(
    var context: Context,
    var supportFragmentManager: FragmentManager,
    var tabCount: Int
) : FragmentPagerAdapter(supportFragmentManager) {
    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> {
                HomeFragment()
            }
            1 -> {
                SportFragment()
            }
            2 -> {
                MovieFragment()
            }
            else -> null
        }
    }
}