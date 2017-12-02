package com.infotechincubator.sharmal.list

import com.infotechincubator.sharmal.base.AbstractViewModel
import com.infotechincubator.sharmal.model.Repo
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by kyawagwin on 30/11/17.
 */
class RepoViewModel(val repo: Repo): AbstractViewModel() {

    private val clicks = PublishSubject.create<Unit>()

    fun getName() = repo.fullName

    fun getDescription() = repo.description

    fun onClick() {
        clicks.onNext(Unit)
    }

    fun clicks(): Observable<Unit> = clicks.hide()
}