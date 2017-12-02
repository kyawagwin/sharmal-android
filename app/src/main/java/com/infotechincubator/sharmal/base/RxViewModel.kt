package com.infotechincubator.sharmal.base

import android.support.annotation.CallSuper
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject

/**
 * Created by kyawagwin on 29/11/17.
 */
abstract class RxViewModel: AbstractViewModel() {

    protected val disposables: CompositeDisposable = CompositeDisposable()
    private val viewState: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)

    @CallSuper
    override fun bind() {
        super.bind()

        viewState.onNext(true)
    }

    @CallSuper
    override fun unbind() {
        super.unbind()

        viewState.onNext(false)
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()

        viewState.onComplete()
    }

    private fun clearSubscriptions() {
        disposables.clear()
    }

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun getViewState(): Observable<Boolean> = viewState.hide()
}