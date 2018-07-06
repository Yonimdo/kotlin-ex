package com.mdo.yoni.eshop.viewHelpers;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Copyright © 2016 AsianTech inc.
 * Created by Vinhhlb on 1/11/16.
 */
public class UnSwipeViewPager extends ViewPager {

    private boolean mCanScrollHorizontally;

    public UnSwipeViewPager(Context context) {
        super(context);
    }

    public UnSwipeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (mCanScrollHorizontally) {
            return super.onInterceptTouchEvent(arg0);
        }
        return false;
    }

    @Override
    public boolean canScrollHorizontally(int direction) {
        if (mCanScrollHorizontally) {
            return super.canScrollHorizontally(direction);
        }
        return false;
    }

    public void setCanScrollHorizontally(boolean canScrollHorizontally) {
        if (canScrollHorizontally == mCanScrollHorizontally) {
            return;
        }
        this.mCanScrollHorizontally = canScrollHorizontally;
        invalidate();
    }
}