package com.infotechincubator.sharmal.network

import android.net.ConnectivityManager
import io.reactivex.Completable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by kyawagwin on 29/11/17.
 */
@Singleton
class NetworkInteractorImpl @Inject constructor(
        private val connectivityManager: ConnectivityManager
): NetworkInteractor {

    override fun hasNetworkConnection(): Boolean =
            connectivityManager.activeNetworkInfo.isConnectedOrConnecting

    override fun hasNetworkConnectionCompletable(): Completable =
            if(hasNetworkConnection()) {
                Completable.complete()
            } else {
                Completable.error { NetworkInteractor.NetworkUnavailableException() }
            }
}