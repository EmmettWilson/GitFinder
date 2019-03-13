package io.github.wilson.emmett.gitfinder

import android.util.Log
import io.github.wilson.emmett.gitfinder.githubService.Command
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class ErrorStateManager {
    private val stateSubject = PublishSubject.create<ErrorState>()

    fun publishError(throwable: Throwable? = null, retryCommand: Command, defaultMessage: String) {
        Log.e("", defaultMessage, throwable)
        val message = throwable?.message ?: defaultMessage
        stateSubject.onNext(ErrorState(message, retryCommand))
    }

    fun getErrorObservable(): Observable<ErrorState> = stateSubject

}

data class ErrorState(val message : String, val retryCommand: Command)
