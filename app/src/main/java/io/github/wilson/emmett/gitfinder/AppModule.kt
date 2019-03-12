package io.github.wilson.emmett.gitfinder

import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
   viewModel { GitRepoViewModel() }
}