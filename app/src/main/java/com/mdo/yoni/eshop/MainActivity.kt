package com.mdo.yoni.eshop

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.mdo.yoni.eshop.adapters.EFragmentPageAdapter
import com.mdo.yoni.eshop.adapters.WordsAdapter
import com.mdo.yoni.eshop.data.getSearchWords
import com.mdo.yoni.eshop.data.internal.EShopDatabase
import com.mdo.yoni.eshop.data.models.Item
import com.mdo.yoni.eshop.data.setSearchWords
import com.mdo.yoni.eshop.dialogs.SearchWordsDialog
import com.mdo.yoni.eshop.dialogs.SearchWordsDialog.canceledWords
import com.mdo.yoni.eshop.fragments.*
import com.xiaofeng.flowlayoutmanager.FlowLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.concurrent.Callable


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
                pagerAdapter = EFragmentPageAdapter(supportFragmentManager)
                viewPager.adapter = pagerAdapter
            });
        })
        pagerAdapter = EFragmentPageAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    @Synchronized
    fun seachWordDismiss(v: View) {
        if (canceledWords != null) {
            canceledWords.add(v.getTag().toString())
        }
        setSearchWords(this, getSearchWords(this)!!.filter { str -> !str.equals(v.getTag()) })
        (asymmetricGridView.adapter as WordsAdapter).refresh();
        pagerAdapter = EFragmentPageAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter}

    @Synchronized
    fun itemDismiss(v: View, after: Callable<Unit>? = null) {
        val item: Item = v.tag as Item
        item.discarded = if (item.discarded == 0) 1 else 0
        val ctx = this
        doAsync {
            EShopDatabase.getInstance(ctx).itemsDao().update(item)
            uiThread {
                after?.call()
            }
        }
    }

    @Synchronized
    fun itemAddToCart(v: View, after: Callable<Unit>? = null) {
        val item: Item = v.tag as Item
        item.incart = if (item.incart == 0) 1 else 0
        val ctx = this
        doAsync {
            EShopDatabase.getInstance(ctx).itemsDao().update(item)
            uiThread {
                after?.call()
            }
        }
    }

    @Synchronized
    fun itemAddToCompare(v: View, after: Callable<Unit>? = null) {
        val item: Item = v.tag as Item
        item.incompare = if (item.incompare == 0) 1 else 0
        val ctx = this
        doAsync {
            EShopDatabase.getInstance(ctx).itemsDao().update(item)
            uiThread {
                after?.call()
            }
        }
    }

}
