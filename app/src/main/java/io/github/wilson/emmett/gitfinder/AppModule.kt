package io.github.wilson.emmett.gitfinder

import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

const val MAIN_THREAD_SCHEDULER = "MAIN_THREAD_SCHEDULER"

val appModule = module {

    single {
        GitRepoRepository(get())
    }

    single(MAIN_THREAD_SCHEDULER) {
        AndroidSchedulers.mainThread()
    }

    viewModel { GitRepoViewModel(get(), get(), get(MAIN_THREAD_SCHEDULER)) }
}