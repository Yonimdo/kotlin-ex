package com.mdo.yoni.eshop.fragments

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

// 1
class EFragmentPageAdapter(fragmentManager: FragmentManager) :
        FragmentPagerAdapter(fragmentManager) {


    // 2
    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return ShopViewFragment.newInstance()
        } else if (position == 1) {
            return BrowseShopFragment.newInstance()
        } else if (position == 2) {
            return CompareFragment.newInstance()
        } else if (position == 3) {
            return CartViewFragment.newInstance()
        } else if (position == 4) {
            return ProfileFragment.newInstance()
        }

        return ShopViewFragment.newInstance()
    }

    // 3
    override fun getCount(): Int {

        return 5
    }
}