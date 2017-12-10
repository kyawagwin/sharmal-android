package com.infotechincubator.sharmal

import android.app.Application
import com.cloudinary.android.MediaManager
import android.os.StrictMode



/**
 * Created by kyawagwin on 29/11/17.
 */
class SharmalApp: Application() {

    companion object {
        lateinit var graph: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        MediaManager.init(this)

        initDependencyGraph()
    }

    private fun initDependencyGraph() {
        graph = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        graph.injectTo(this)
    }
}