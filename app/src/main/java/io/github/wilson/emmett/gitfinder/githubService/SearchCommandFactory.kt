package io.github.wilson.emmett.gitfinder.githubService

import io.github.wilson.emmett.gitfinder.ErrorStateManager
import io.github.wilson.emmett.gitfinder.domain.GithubRepoDatabase

class SearchCommandFactory(
    private val githubApi: GithubApi,
    private val database: GithubRepoDatabase,
    private val errorStateManager: ErrorStateManager
) {
    fun makeCommand(string: String): SearchCommand {
        return SearchCommand(string, githubApi, database, errorStateManager)
    }
}
