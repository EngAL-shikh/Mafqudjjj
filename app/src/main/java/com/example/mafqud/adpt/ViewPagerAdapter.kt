package com.example.mafqud

/**
 * Created by Waleed_ALmadhab on 3/4/2019.
 */


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class ViewPagerAdapter(fraManager: FragmentManager): FragmentPagerAdapter(fraManager) {

    var fragments=ArrayList<Fragment>()
    var tabTitles=ArrayList<String>()

    fun addFragment(fragment: Fragment, title: String) {
        fragments.add(fragment)
        tabTitles.add(title)
    }


    override fun getItem(position: Int): Fragment {
        return fragments[position]    }

    override fun getCount(): Int {
        return fragments.size

    }


    override fun getPageTitle(position: Int): CharSequence {
        return tabTitles[position]
    }

}