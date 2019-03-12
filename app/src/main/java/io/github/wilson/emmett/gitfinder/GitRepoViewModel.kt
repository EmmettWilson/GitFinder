package io.github.wilson.emmett.gitfinder

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.Scheduler

class GitRepoViewModel(private val gitRepoRepository: GitRepoRepository,
                       private val mainThread : Scheduler) : ViewModel() {
    fun getText() = "Hello Koin"

    fun getRepositories() : Observable<List<RepositoryDto>> {
        return gitRepoRepository.getRepositories("koin")
            .observeOn(mainThread)

    }
}