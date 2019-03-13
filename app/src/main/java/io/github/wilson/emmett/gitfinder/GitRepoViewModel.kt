package io.github.wilson.emmett.gitfinder

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import io.reactivex.BackpressureStrategy
import io.reactivex.Scheduler

class GitRepoViewModel(private val gitRepoRepository: GitRepoRepository,
                       private val errorStateManager: ErrorStateManager,
                       private val mainThreadScheduler: Scheduler) : ViewModel() {

    val gitRepositories by lazy {
        gitRepoRepository.getRepositories()
    }

    fun getErrorState() = LiveDataReactiveStreams.fromPublisher( errorStateManager.getErrorObservable().observeOn(mainThreadScheduler).toFlowable(BackpressureStrategy.LATEST))
}
