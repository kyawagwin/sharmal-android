package com.infotechincubator.sharmal.base

/**
 * Created by kyawagwin on 29/11/17.
 */
interface ViewModel {

    fun bind()

    fun unbind()

    fun onDestroy()
}