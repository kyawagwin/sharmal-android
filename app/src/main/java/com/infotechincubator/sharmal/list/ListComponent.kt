package com.infotechincubator.sharmal.list

import com.infotechincubator.sharmal.base.ActivityScope
import dagger.Subcomponent

/**
 * Created by kyawagwin on 1/12/17.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(
        ListModule::class
))
interface ListComponent {
    fun injectTo(activity: ListActivity)
}