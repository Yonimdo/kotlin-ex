package com.mdo.yoni.eshop

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import com.mdo.yoni.eshop.fragments.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_shop_view.view.*


class MainActivity : AppCompatActivity(), ShopViewFragment.OnFragmentInteractionListener,
        SingleItemFragment.OnFragmentInteractionListener,
        CompareFragment.OnFragmentInteractionListener,
        CartViewFragment.OnFragmentInteractionListener,
        ProfileFragment.OnFragmentInteractionListener,
        BrowseShopFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var pagerAdapter: EFragmentPageAdapter


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.navigation_home -> {
//                message.setText(R.string.title_home)
                viewPager.setCurrentItem(0);
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_browse -> {
                viewPager.setCurrentItem(1);

//                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_compare -> {
                viewPager.setCurrentItem(2);
//                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_cart -> {
                viewPager.setCurrentItem(3);

//                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                viewPager.setCurrentItem(4);
//                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pagerAdapter = EFragmentPageAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
