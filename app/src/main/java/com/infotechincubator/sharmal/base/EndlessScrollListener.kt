package com.infotechincubator.sharmal.base

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.AbsListView

/**
 * Created by kyawagwin on 2/12/17.
 */
abstract class EndlessScrollListener constructor(
        // The minimum number of items to have below your current scroll position before loading more
        val visibleThreshold: Int = 5,
        // The current offset index of data you have loaded
        var currentPage: Int = 0,
        // Set the starting page index
        val startingPageIndex: Int = 0,
        // Set the layout
        val layoutManager: RecyclerView.LayoutManager
): RecyclerView.OnScrollListener() {

    // The total number of items in the dataset after the last load
    private var previousTotalItemCount = 0
    // If we are still waiting for the last set of data to load
    private var loading = true


}