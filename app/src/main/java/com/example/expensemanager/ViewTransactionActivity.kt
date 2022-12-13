package com.example.expensemanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.*

class ViewTransactionActivity : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_transaction)

        initView()
    }

    private fun initView() {
        findViewById<TabView>(R.id.tabLayout)
        findViewById<ViewPager>(R.id.viewPager)
        tabLayout.addTab(tabLayout.newTab().setText("ALL"));
        tabLayout.addTab(tabLayout.newTab().setText("EXPENSE"));
        tabLayout.addTab(tabLayout.newTab().setText("INCOME"));

        viewPager.addOnPageChangeListener(TabLayoutOnPageChangeListener(tabLayout))

        var adapter = TabLayoutAdapter(this,supportFragmentManager,tabLayout.tabCount)


        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: Tab) {}
            override fun onTabReselected(tab: Tab) {}
        })
    }
}