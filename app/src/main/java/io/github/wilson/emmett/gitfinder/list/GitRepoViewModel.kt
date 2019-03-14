package io.github.wilson.emmett.gitfinder.list

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import io.github.wilson.emmett.gitfinder.ErrorStateManager
import io.github.wilson.emmett.gitfinder.GitRepoRepository
import io.reactivex.BackpressureStrategy

class GitRepoViewModel(
    private val gitRepoRepository: GitRepoRepository,
    private val errorStateManager: ErrorStateManager
) : ViewModel() {

    val gitRepositories by lazy {
        LiveDataReactiveStreams.fromPublisher(
            gitRepoRepository.getRepositories()
                .map { repos -> repos.sortedByDescending { it.watchers } }
                .toFlowable(BackpressureStrategy.LATEST)
        )
    }

    fun getErrorState() = LiveDataReactiveStreams.fromPublisher(
        errorStateManager
            .getErrorObservable()
            .toFlowable(BackpressureStrategy.LATEST)
    )
}
