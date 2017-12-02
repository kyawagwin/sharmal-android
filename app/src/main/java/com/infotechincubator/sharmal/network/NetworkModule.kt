package com.infotechincubator.sharmal.network

import android.content.Context
import android.net.ConnectivityManager
import com.infotechincubator.sharmal.AppQualifier
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by kyawagwin on 29/11/17.
 */
@Module
class NetworkModule {

    @Provides @Singleton
    fun provideConnectivityManager(@AppQualifier context: Context): ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Provides @Singleton
    fun provideNetworkInteractor(networkInteractorImpl: NetworkInteractorImpl): NetworkInteractor =
            networkInteractorImpl
}