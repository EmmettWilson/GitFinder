package io.github.wilson.emmett.gitfinder.githubService

import android.annotation.SuppressLint
import io.github.wilson.emmett.gitfinder.ErrorStateManager
import io.github.wilson.emmett.gitfinder.domain.GitRepo
import io.github.wilson.emmett.gitfinder.domain.GithubRepoDatabase

class SearchCommand(
    private val string: String,
    private val githubApi: GithubApi,
    private val database: GithubRepoDatabase,
    private val errorStateManager: ErrorStateManager
) : Command {
    @SuppressLint("CheckResult")
    override fun execute() {
        githubApi.reposForOrganization(string)
            .map { repos -> repos.map{ GitRepo.from(it)} }
            .subscribe({
                database.clearAllTables()
                database.repoDao().insertRepos(it)
            }, {
                errorStateManager.publishError(it, this,"Could not search for Organization")
            })
    }

}
