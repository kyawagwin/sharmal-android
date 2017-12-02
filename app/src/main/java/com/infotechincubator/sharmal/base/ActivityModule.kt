package com.infotechincubator.sharmal.base

import android.content.Context
import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides

/**
 * Created by kyawagwin on 29/11/17.
 */
@Module
abstract class ActivityModule(
        private val activity: AppCompatActivity
) {
    @Provides
    fun provideActivity(): AppCompatActivity = activity

    @Provides
    fun provideActivityContext(): Context = activity.baseContext
}