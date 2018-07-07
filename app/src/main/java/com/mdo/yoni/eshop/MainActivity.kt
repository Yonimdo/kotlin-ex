package com.mdo.yoni.eshop

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.mdo.yoni.eshop.adapters.WordsAdapter
import com.mdo.yoni.eshop.data.getCompareIds
import com.mdo.yoni.eshop.data.getSearchWords
import com.mdo.yoni.eshop.data.setCompareIds
import com.mdo.yoni.eshop.data.setSearchWords
import com.mdo.yoni.eshop.dialogs.SearchWordsDialog
import com.mdo.yoni.eshop.fragments.*
import com.xiaofeng.flowlayoutmanager.FlowLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


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
                viewPager.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_browse -> {
                viewPager.currentItem = 1

//                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_compare -> {
                viewPager.currentItem = 2
//                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_cart -> {
                viewPager.currentItem = 3

//                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                viewPager.currentItem = 4
//                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        val flowLayoutManager = FlowLayoutManager()
        flowLayoutManager.setAutoMeasureEnabled(true)
        asymmetricGridView.layoutManager = flowLayoutManager
        asymmetricGridView.adapter = WordsAdapter(this)
        btnSearch.setOnClickListener(View.OnClickListener { v ->
            SearchWordsDialog(this).open(SearchWordsDialog.onSelected {
                (asymmetricGridView.adapter as WordsAdapter).refresh();
            });
        })
        pagerAdapter = EFragmentPageAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    fun seachWordCancel(v: View) {
        setSearchWords(this, getSearchWords(this).filter { str -> !str.equals(v.getTag()) })
        (asymmetricGridView.adapter as WordsAdapter).refresh();
    }

    fun toogleCompare(v: View) {
        if (v.getTag().toString() in getCompareIds(this)) {
            removeFromCompare(v)
        } else {
            addToCompare(v)
        }

    }

    fun addToCompare(v: View) {
        val list = getCompareIds(this)
        list.add(v.getTag().toString())
        setCompareIds(this, list)
    }

    fun removeFromCompare(v: View) {
        val list = getCompareIds(this).filter { str -> str.equals(v.getTag().toString()) }
        setCompareIds(this, ArrayList(list))
    }
}
