package io.github.wilson.emmett.gitfinder

import androidx.lifecycle.LiveData
import io.github.wilson.emmett.gitfinder.domain.GitRepo
import io.github.wilson.emmett.gitfinder.domain.GithubRepoDatabase
import io.github.wilson.emmett.gitfinder.githubService.GithubApi
import io.github.wilson.emmett.gitfinder.githubService.RepositoryDto
import io.reactivex.Observable

class GitRepoRepository(private val githubApi: GithubApi, private val database : GithubRepoDatabase) {
    fun getRepositories(): LiveData<List<GitRepo>> {
        return database.gitRepoDao().getRepos()
    }

    fun searchRepositories(organizationName: String) {
        githubApi.reposForOrganization(organizationName)
            .map { it.body() }
            .map { repos -> repos.map{GitRepo.from(it)} }
            .doOnNext{
                database.clearAllTables()
                database.gitRepoDao().insertRepos(it)
            }
            .subscribe{}
    }
}
