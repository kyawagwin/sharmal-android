package com.infotechincubator.sharmal.main

import com.infotechincubator.sharmal.base.ActivityScope
import dagger.Subcomponent

/**
 * Created by kyawagwin on 1/12/17.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(
        MainModule::class
))
interface MainComponent {

    fun injectTo(activity: MainActivity)
}