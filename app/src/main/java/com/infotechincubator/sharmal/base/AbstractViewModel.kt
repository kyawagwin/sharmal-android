package com.infotechincubator.sharmal.base

import android.databinding.BaseObservable

/**
 * Created by kyawagwin on 29/11/17.
 */
abstract class AbstractViewModel: BaseObservable(), ViewModel {

    override fun bind() {

    }

    override fun unbind() {

    }

    override fun onDestroy() {
        // Hook for subclasses to clean up used resources
    }
}