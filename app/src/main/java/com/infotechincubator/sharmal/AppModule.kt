package com.infotechincubator.sharmal

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by kyawagwin on 29/11/17.
 */
@Module
class AppModule(
        private val app: SharmalApp
) {

    @Provides @Singleton
    fun provideApp(): Application = app

    @Provides @Singleton @AppQualifier
    fun provideContext(): Context = app.baseContext
}