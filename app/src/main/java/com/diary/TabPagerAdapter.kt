package com.diary

import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabPagerAdapter(private var adapter: FragmentManager) : FragmentPagerAdapter(adapter) {
    private var numOfTabs : Int = 4
    private val frList : ArrayList<Fragment> = ArrayList<Fragment>()
    private val frTitleList : ArrayList<String> = ArrayList<String>()
    override fun getCount(): Int {
        return numOfTabs
    }

    override fun getItem(position: Int): Fragment {
        return frList.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return frTitleList.get(position)
    }
    fun addFragment(fr: Fragment, title: String) {
        frList.add(fr)
        frTitleList.add(title)
    }
}
