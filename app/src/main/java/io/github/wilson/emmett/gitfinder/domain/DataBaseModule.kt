package io.github.wilson.emmett.gitfinder.domain

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            GithubRepoDatabase::class.java,
            "github_repo_database"
        ).build()
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            GithubRepoDatabase::class.java,
            "github_repo_database"
        ).build().repoDao()
    }
}
