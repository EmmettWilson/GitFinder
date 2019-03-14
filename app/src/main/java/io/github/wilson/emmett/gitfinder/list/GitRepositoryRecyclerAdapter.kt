package io.github.wilson.emmett.gitfinder.list

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import io.github.wilson.emmett.gitfinder.R
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
        val currentRepo = repos[position]

        holder.name.text = currentRepo.full_name
        holder.description.text = currentRepo.description
        holder.language.text = currentRepo.language
        holder.watchers.text = "${currentRepo.watchers}"

        holder.itemView.setOnClickListener {
            val intent = CustomTabsIntent.Builder()
                .setToolbarColor(ContextCompat.getColor(holder.context,
                    R.color.silver
                ))
                .build()
            intent.launchUrl(holder.context, Uri.parse(currentRepo.html_url))
        }
    }
}

class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val context = itemView.context
    val name = itemView.repoName
    val description = itemView.repoDescription
    val language = itemView.languageText
    val watchers = itemView.watchersText

}
