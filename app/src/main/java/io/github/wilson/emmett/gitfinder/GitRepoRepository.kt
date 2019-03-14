package io.github.wilson.emmett.gitfinder

import androidx.lifecycle.LiveData
import io.github.wilson.emmett.gitfinder.domain.GitRepo
import io.github.wilson.emmett.gitfinder.domain.GithubRepoDatabase

class GitRepoRepository(private val database: GithubRepoDatabase) {
    fun getRepositories(): LiveData<List<GitRepo>> {
        return database.repoDao().getRepos()
    }
}
