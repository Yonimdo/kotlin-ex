package com.mdo.yoni.eshop.adapters

import android.content.Context
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
import android.widget.TextView
import com.mdo.yoni.eshop.R
import com.mdo.yoni.eshop.data.models.Item
import com.mindorks.placeholderview.annotations.Layout
import com.mindorks.placeholderview.annotations.View

@Layout(R.layout.item_card_view)
class CardSwipeAdapter(private val mContext: Context, private val mItem: Item, private val mSwipeView: SwipePlaceHolderView) {



    @View(R.id.rejectBtn)
    private val rejectBtn: ImageButton? = null


    @View(R.id.acceptBtn)
    private val acceptBtn: ImageButton? = null


    @View(R.id.profileImageView)
    private val profileImageView: ImageView? = null

    @View(R.id.nameAgeTxt)
    private val nameAgeTxt: TextView? = null

    @View(R.id.locationNameTxt)
    private val locationNameTxt: TextView? = null

    @Resolve
    private fun onResolved() {

        Glide.with(mContext).load(mItem.url).into(profileImageView)
        val text = mItem.name + ", " + mItem.price
        nameAgeTxt!!.text = text
        locationNameTxt!!.text = mItem.keywords
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

    init{
        rejectBtn?.setOnClickListener(object : android.view.View.OnClickListener {
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

}


