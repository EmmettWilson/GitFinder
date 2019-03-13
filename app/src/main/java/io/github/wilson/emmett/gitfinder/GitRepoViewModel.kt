package io.github.wilson.emmett.gitfinder

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Scheduler
import org.reactivestreams.Publisher

class GitRepoViewModel(private val gitRepoRepository: GitRepoRepository,
                       private val errorStateManager: ErrorStateManager,
                       private val mainThreadScheduler: Scheduler) : ViewModel() {

    fun getRepositories() = gitRepoRepository.getRepositories()


    fun getErrorState() = LiveDataReactiveStreams.fromPublisher( errorStateManager.getErrorObservable().observeOn(mainThreadScheduler).toFlowable(BackpressureStrategy.LATEST))
}
