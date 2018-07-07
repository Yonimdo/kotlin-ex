package com.mdo.yoni.eshop.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState
import com.mindorks.placeholderview.annotations.swipe.SwipeInState
import com.mindorks.placeholderview.annotations.swipe.SwipeIn
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState
import com.mindorks.placeholderview.annotations.swipe.SwipeOut
import com.bumptech.glide.Glide
import com.mindorks.placeholderview.annotations.Resolve
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mdo.yoni.eshop.R
import com.mdo.yoni.eshop.data.models.Item
import com.mindorks.placeholderview.annotations.Layout
import com.mindorks.placeholderview.annotations.View
import com.xiaofeng.flowlayoutmanager.FlowLayoutManager

@Layout(R.layout.item_card_view)
class CardSwipeAdapter(private val mContext: Context, private val mItem: Item, private val mSwipeView: SwipePlaceHolderView) {

    @View(R.id.compareBtn)
    private val compareBtn: ImageButton? = null


    @View(R.id.dismissBtn)
    private val dismissBtn: ImageButton? = null


    @View(R.id.acceptBtn)
    private val acceptBtn: ImageButton? = null


    @View(R.id.profileImageView)
    private val profileImageView: ImageView? = null

    @View(R.id.keywords)
    private val keywords: RecyclerView? = null

    @Resolve
    private fun onResolved() {
        Glide.with(mContext).load(mItem.url).into(profileImageView)
        val text = mItem.name + ", " + mItem.price
        val flowLayoutManager = FlowLayoutManager()
        flowLayoutManager.setAutoMeasureEnabled(true)
        keywords?.layoutManager = flowLayoutManager
        keywords?.adapter = KeywordsAdapter(mContext, mItem.keywords!!.split(","))
        compareBtn?.setOnClickListener(object : android.view.View.OnClickListener {
            override fun onClick(v: android.view.View) {
                mSwipeView.doSwipe(true)
            }
        })
        dismissBtn?.setOnClickListener(object : android.view.View.OnClickListener {
            override fun onClick(v: android.view.View) {
                mSwipeView.doSwipe(false)
            }
        })
        acceptBtn?.setOnClickListener(object : android.view.View.OnClickListener {
            override fun onClick(v: android.view.View) {
                mSwipeView.doSwipe(true)
            }
        })
    }

    @SwipeOut
    private fun onSwipedOut() {
        Log.d("EVENT", "onSwipedOut")
        mSwipeView.addView(this)
    }

    @SwipeCancelState
    private fun onSwipeCancelState() {
        Log.d("EVENT", "onSwipeCancelState")
    }

    @SwipeIn
    private fun onSwipeIn() {
        Log.d("EVENT", "onSwipedIn")
    }

    @SwipeInState
    private fun onSwipeInState() {
        Log.d("EVENT", "onSwipeInState")
    }

    @SwipeOutState
    private fun onSwipeOutState() {
        Log.d("EVENT", "onSwipeOutState")
    }

    init {
    }

}


