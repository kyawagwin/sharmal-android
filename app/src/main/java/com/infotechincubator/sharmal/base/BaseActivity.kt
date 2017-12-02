package com.infotechincubator.sharmal.base

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import com.infotechincubator.sharmal.AppComponent
import com.infotechincubator.sharmal.SharmalApp

/**
 * Created by kyawagwin on 29/11/17.
 */
abstract class BaseActivity: AppCompatActivity() {

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectDependencies(SharmalApp.graph)
    }

    abstract fun injectDependencies(graph: AppComponent)
}