package io.github.wilson.emmett.gitfinder

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.github.wilson.emmett.gitfinder.domain.GitRepo
import io.github.wilson.emmett.gitfinder.githubService.RepositoryDto
import io.reactivex.Observable
import io.reactivex.Scheduler

class GitRepoViewModel(private val gitRepoRepository: GitRepoRepository,
                       private val mainThread : Scheduler) : ViewModel() {

    fun getRepositories(): LiveData<List<GitRepo>> {
        return gitRepoRepository.getRepositories()
    }

    fun searchRepositories(string: String) =  gitRepoRepository.searchRepositories(string)
}
