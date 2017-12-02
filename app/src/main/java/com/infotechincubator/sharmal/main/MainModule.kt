package com.infotechincubator.sharmal.main

import android.support.v7.app.AppCompatActivity
import com.infotechincubator.sharmal.base.ActivityModule
import dagger.Module
import dagger.Provides

/**
 * Created by kyawagwin on 1/12/17.
 */
@Module
class MainModule(
        activity: AppCompatActivity,
        val data: String
): ActivityModule(activity) {

    @Provides
    fun provideData(): String = data
}