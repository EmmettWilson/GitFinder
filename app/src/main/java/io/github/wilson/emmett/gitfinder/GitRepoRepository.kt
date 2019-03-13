package io.github.wilson.emmett.gitfinder

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import io.github.wilson.emmett.gitfinder.domain.GitRepo
import io.github.wilson.emmett.gitfinder.domain.GithubRepoDatabase
import io.github.wilson.emmett.gitfinder.githubService.GithubApi

class GitRepoRepository(private val githubApi: GithubApi,
                        private val database : GithubRepoDatabase) {
    fun getRepositories(): LiveData<List<GitRepo>> {
        return database.repoDao().getRepos()
    }

    @SuppressLint("CheckResult")
    fun searchRepositories(organizationName: String) {

    }
}
