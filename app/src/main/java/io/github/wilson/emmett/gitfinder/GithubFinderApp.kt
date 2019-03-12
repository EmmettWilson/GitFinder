package io.github.wilson.emmett.gitfinder

import android.app.Application
import io.github.wilson.emmett.gitfinder.githubService.apiModule
import org.koin.android.ext.android.startKoin

class GithubFinderApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, apiModule))
    }
}