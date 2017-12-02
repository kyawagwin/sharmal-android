package com.infotechincubator.sharmal.list

import android.util.Log
import com.infotechincubator.sharmal.Constants
import com.infotechincubator.sharmal.api.GithubApiService
import com.infotechincubator.sharmal.base.RxViewModel
import com.infotechincubator.sharmal.model.Repo
import com.infotechincubator.sharmal.model.SearchResponse
import com.infotechincubator.sharmal.network.NetworkInteractor
import io.github.plastix.rxdelay.RxDelay
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Created by kyawagwin on 30/11/17.
 */
class ListViewModel @Inject constructor(
        private val apiService: GithubApiService,
        private val networkInteractor: NetworkInteractor
): RxViewModel() {

    private var networkRequest: Disposable = Disposables.disposed()

    private var repos: BehaviorSubject<List<Repo>> = BehaviorSubject.createDefault(emptyList())
    private var loadingState: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)
    private val fetchErrors: PublishSubject<Throwable> = PublishSubject.create()
    private val networkErrors: PublishSubject<Throwable> = PublishSubject.create()

    fun fetchRepos() {
        networkRequest = networkInteractor.hasNetworkConnectionCompletable()
                .andThen(apiService.repoSearch(Constants.SEARCH_QUERY_KOTLIN,
                        Constants.SEARCH_SORT_STARS,
                        Constants.SEARCH_ORDER_DESCENDING))
                .subscribeOn(Schedulers.io())
                .compose(RxDelay.delaySingle(getViewState()))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    networkRequest.dispose()
                    loadingState.onNext(true)
                }
                .doOnEvent { searchResponse, throwable ->
                    loadingState.onNext(false)
                }
                .subscribeWith(object: DisposableSingleObserver<SearchResponse>() {
                    override fun onSuccess(value: SearchResponse) {
                        repos.onNext(value.repos)
                    }

                    override fun onError(e: Throwable) {
                        when(e) {
                            is NetworkInteractor.NetworkUnavailableException -> networkErrors.onNext(e)
                            else -> fetchErrors.onNext(e)
                        }
                    }
                })

        addDisposable(networkRequest)
    }

    fun getRepos(): Observable<List<Repo>> = repos.hide()

    fun fetchErrors(): Observable<Throwable> = fetchErrors.hide()

    fun networkErrors(): Observable<Throwable> = networkErrors.hide()

    fun loadingState(): Observable<Boolean> = loadingState.hide()
}