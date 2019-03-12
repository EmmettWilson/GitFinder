package io.github.wilson.emmett.gitfinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.wilson.emmett.gitfinder.domain.GitRepo
import kotlinx.android.synthetic.main.repo_list_item.view.*

class GitRepositoryRecyclerAdapter : RecyclerView.Adapter<RepoViewHolder>() {

    private var repos: List<GitRepo> = emptyList()

    fun setData(repos: List<GitRepo>) {
        this.repos = repos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_list_item, parent, false)
        return RepoViewHolder(view)
    }

    override fun getItemCount() = repos.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.itemView.repoNameTextView.text = repos[position].full_name
    }

}

class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    //TODo Hold access to item here so we do not do unnecessary lookups

}
