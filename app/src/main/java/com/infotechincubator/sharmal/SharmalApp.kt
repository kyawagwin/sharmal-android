package com.infotechincubator.sharmal

import android.app.Application

/**
 * Created by kyawagwin on 29/11/17.
 */
class SharmalApp: Application() {

    companion object {
        lateinit var graph: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        initDependencyGraph()
    }

    private fun initDependencyGraph() {
        graph = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        graph.injectTo(this)
    }
}