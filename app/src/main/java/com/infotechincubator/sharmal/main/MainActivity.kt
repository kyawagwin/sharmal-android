package com.infotechincubator.sharmal.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.infotechincubator.sharmal.AppComponent
import com.infotechincubator.sharmal.R
import com.infotechincubator.sharmal.base.ViewModelActivity
import com.infotechincubator.sharmal.databinding.ActivityMainBinding

class MainActivity : ViewModelActivity<MainViewModel, ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onBind() {
        super.onBind()

        binding.viewModel = viewModel
    }

    override fun injectDependencies(graph: AppComponent) {
        graph.plus(MainModule(this, "Hello World!")).injectTo(this)
    }

    override fun getViewBinding(): ActivityMainBinding {
        return DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}
