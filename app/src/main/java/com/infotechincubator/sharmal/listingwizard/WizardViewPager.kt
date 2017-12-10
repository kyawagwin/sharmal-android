package com.infotechincubator.sharmal.listingwizard

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * Created by kyawagwin on 9/12/17.
 */
class WizardViewPager constructor(
        context: Context,
        attrs: AttributeSet
): ViewPager(context, attrs) {

    private var pagingEnabled = true

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        if(this.pagingEnabled) {
            return super.onTouchEvent(ev)
        }

        return false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if(this.pagingEnabled) {
            return super.onInterceptTouchEvent(ev)
        }

        return false
    }

    fun setPagingEnabled(enabled: Boolean) {
        pagingEnabled = enabled
    }
}