package com.mdo.yoni.eshop.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup
import com.mdo.yoni.eshop.data.models.Item
import com.mdo.yoni.eshop.fragments.SingleItemFragment

class BrowsePageAdapter(fragmentManager: FragmentManager, private var arr: List<Item>, private val parent: Fragment? = null) :
        FragmentPagerAdapter(fragmentManager) {

    // 2
    override fun getItem(position: Int): Fragment {
        return SingleItemFragment.newInstance(arr.get(position), parent)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val frag = super.instantiateItem(container, position)
        (frag as SingleItemFragment).item = arr.get(position)
        return frag
    }

    // 3
    override fun getCount(): Int {
        return arr.size
    }
}