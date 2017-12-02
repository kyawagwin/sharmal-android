package com.infotechincubator.sharmal.network

import io.reactivex.Completable

/**
 * Created by kyawagwin on 28/11/17.
 */
interface NetworkInteractor {

    fun hasNetworkConnection(): Boolean

    fun hasNetworkConnectionCompletable(): Completable

    class NetworkUnavailableException: Throwable("No network available!")
}