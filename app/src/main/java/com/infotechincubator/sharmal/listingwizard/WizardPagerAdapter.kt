package com.infotechincubator.sharmal.listingwizard

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by kyawagwin on 9/12/17.
 */
class WizardPagerAdapter constructor(
        fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(fragmentManager) {

    private var fragments: Array<Fragment> = emptyArray()

    fun addFragments(fragments: Array<Fragment>) {
        this.fragments = fragments
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}