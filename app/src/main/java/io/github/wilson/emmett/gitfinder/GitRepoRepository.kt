package io.github.wilson.emmett.gitfinder

import io.github.wilson.emmett.gitfinder.domain.GitRepo
import io.github.wilson.emmett.gitfinder.domain.GithubRepoDatabase
import io.reactivex.Observable

class GitRepoRepository(private val database: GithubRepoDatabase) {
    fun getRepositories(): Observable<List<GitRepo>> {
        return database.repoDao().getRepos()

    }
}
