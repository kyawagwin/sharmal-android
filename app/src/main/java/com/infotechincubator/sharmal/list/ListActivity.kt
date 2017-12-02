package com.infotechincubator.sharmal.list

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.infotechincubator.sharmal.AppComponent
import com.infotechincubator.sharmal.R
import com.infotechincubator.sharmal.base.ViewModelActivity
import com.infotechincubator.sharmal.databinding.ActivityListBinding
import com.infotechincubator.sharmal.extension.showSnackbar
import com.infotechincubator.sharmal.model.Repo
import com.infotechincubator.sharmal.util.SimpleDividerItemDecoration
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by kyawagwin on 2/12/17.
 */
class ListActivity: ViewModelActivity<ListViewModel, ActivityListBinding>() {

    @Inject
    lateinit var adapter: RepoAdapter

    @Inject
    lateinit var layoutManager: LinearLayoutManager

    @Inject
    lateinit var dividerDecorator: SimpleDividerItemDecoration

    var disposables: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun injectDependencies(graph: AppComponent) {
        graph.plus(ListModule(this)).injectTo(this)
    }

    override fun getViewBinding(): ActivityListBinding {
        return DataBindingUtil.setContentView(this, R.layout.activity_list)
    }

    override fun onBind() {
        super.onBind()

        binding.viewModel = viewModel
        setupRecyclerView()
        setupSwipeRefresh()

        disposables.add(viewModel.getRepos().subscribe {
            updateList(it)
        })

        disposables.add(viewModel.loadingState().subscribe {
            binding.listSwipeRefresh.isRefreshing = it
        })

        disposables.add(viewModel.fetchErrors().subscribe {
            errorFetchRepos()
        })

        disposables.add(viewModel.networkErrors().subscribe {
            errorNoNetwork()
        })
    }

    private fun updateList(repos: List<Repo>) {
        adapter.updateRepos(repos)
    }

    private fun setupSwipeRefresh() {
        binding.listSwipeRefresh.setOnRefreshListener {
            viewModel.fetchRepos()
        }
    }

    private fun setupRecyclerView() {
        binding.listRecyclerView.adapter = adapter
        binding.listRecyclerView.layoutManager = layoutManager
        binding.listRecyclerView.addItemDecoration(dividerDecorator)
    }

    private fun errorFetchRepos() {
        binding.listRelativeLayout.showSnackbar("Repos fetch error.")
    }

    private fun errorNoNetwork() {
        binding.listRelativeLayout.showSnackbar("No network connection.")
    }

    override fun onPause() {
        super.onPause()

        disposables.clear()
    }
}