package com.infotechincubator.sharmal.list

import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.infotechincubator.sharmal.R
import com.infotechincubator.sharmal.base.ActivityScope
import com.infotechincubator.sharmal.databinding.ItemRepoBinding
import com.infotechincubator.sharmal.model.Repo
import javax.inject.Inject

/**
 * Created by kyawagwin on 1/12/17.
 */
@ActivityScope
class RepoAdapter @Inject constructor(

) : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    private var repos: List<Repo> = emptyList()

    override fun getItemCount(): Int = repos.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RepoViewHolder {
        val binding = DataBindingUtil.inflate<ItemRepoBinding>(
                LayoutInflater.from(parent?.context),
                R.layout.item_repo,
                parent,
                false
        )

        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder?, position: Int) {
        val binding = holder?.binding
        val repo = repos[position]
        var viewModel = binding?.viewModel

        // Unbind old ViewModel if we have one
        viewModel?.unbind()

        // Create new ViewModel, set it, and bind it
        viewModel = RepoViewModel(repo)
        binding?.viewModel = viewModel
        viewModel?.bind()
    }

    fun updateRepos(repos: List<Repo>) {

        val diff = RepoDiffCallback(this.repos, repos)
        val result = DiffUtil.calculateDiff(diff)

        this.repos = repos
        result.dispatchUpdatesTo(this)
    }

    class RepoViewHolder(val binding: ItemRepoBinding): RecyclerView.ViewHolder(binding.root) {


    }
}