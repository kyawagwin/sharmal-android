package com.infotechincubator.sharmal

import com.infotechincubator.sharmal.api.ApiModule
import com.infotechincubator.sharmal.list.ListComponent
import com.infotechincubator.sharmal.list.ListModule
import com.infotechincubator.sharmal.main.MainComponent
import com.infotechincubator.sharmal.main.MainModule
import com.infotechincubator.sharmal.network.NetworkModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by kyawagwin on 29/11/17.
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        NetworkModule::class,
        ApiModule::class
))
interface AppComponent {

    // Injectors
    fun injectTo(app: SharmalApp)

    // Submodule methods
    // Every screen is its own submodule of the graph and must be added here.
    fun plus(module: MainModule): MainComponent

    fun plus(module: ListModule): ListComponent
}