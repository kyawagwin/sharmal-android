package com.infotechincubator.sharmal.main

import com.infotechincubator.sharmal.base.AbstractViewModel
import javax.inject.Inject

/**
 * Created by kyawagwin on 1/12/17.
 */
class MainViewModel @Inject constructor(
        val value: String
): AbstractViewModel() {

    fun getResult() = value
}