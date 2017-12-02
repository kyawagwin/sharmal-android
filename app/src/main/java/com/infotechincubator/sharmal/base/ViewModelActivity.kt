package com.infotechincubator.sharmal.base

import android.databinding.ViewDataBinding
import android.os.Build
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by kyawagwin on 29/11/17.
 */
abstract class ViewModelActivity<T: ViewModel, B: ViewDataBinding>: BaseActivity(), LoaderManager.LoaderCallbacks<T> {

    private val LOADER_ID = 1

    protected lateinit var viewModel: T
    protected lateinit var binding: B

    @Inject
    protected lateinit var viewModelLoaderProvider: Provider<ViewModelLoader<T>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = getViewBinding()

        initLoader()
    }

    @CallSuper
    open protected fun onViewModelProvided(viewModel: T) {
        this.viewModel = viewModel
    }

    @CallSuper
    open protected fun onViewModelReset() {
        // Hook for subclasses
    }

    @CallSuper
    override fun onStart() {
        super.onStart()

        onBind()
    }

    @CallSuper
    open protected fun onBind() {
        viewModel.bind()
    }

    // On Nougat and above onStop is no longer lazy!!
    // This makes sure to unbind our viewModel properly
    // See https://www.bignerdranch.com/blog/android-activity-lifecycle-onStop/
    @CallSuper
    override fun onStop() {
        super.onStop()

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            onUnbind()
        }
    }

    @CallSuper
    override fun onPause() {
        super.onPause()

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            onUnbind()
        }
    }

    @CallSuper
    open protected fun onUnbind() {
        viewModel.unbind()
    }

    abstract fun getViewBinding(): B

    private fun initLoader() {
        supportLoaderManager.initLoader<T>(LOADER_ID, null, this)
    }

    override fun onLoadFinished(loader: Loader<T>?, viewModel: T) {
        onViewModelProvided(viewModel)
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<T> =
            viewModelLoaderProvider.get()

    override fun onLoaderReset(loader: Loader<T>?) {
        onViewModelReset()
    }
}