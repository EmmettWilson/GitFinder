package io.github.wilson.emmett.gitfinder.list

import androidx.lifecycle.LiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jraska.livedata.TestObserver
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.github.wilson.emmett.gitfinder.ErrorStateManager
import io.github.wilson.emmett.gitfinder.GitRepoRepository
import io.github.wilson.emmett.gitfinder.domain.GitRepo
import io.github.wilson.emmett.gitfinder.test
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest

@RunWith(AndroidJUnit4::class)
class GitRepoViewModelTest : AutoCloseKoinTest() {
    private val gitRepoRepository = mock<GitRepoRepository>()
    private val errorStateManager = ErrorStateManager()

    private val testObject = GitRepoViewModel(
        gitRepoRepository,
        errorStateManager
    )

    @Test
    fun getReposReturnsValue() {
        val expected = listOf(GitRepo.test(99))
        whenever(gitRepoRepository.getRepositories()).thenReturn(Observable.just(expected))

        val liveData = testObject.gitRepositories.test()

        liveData.assertValue(expected)
    }

    @Test
    fun getReposSortsByFollowers() {
        val expected = (1..10).map { GitRepo.test(it) }.reversed()
        val shuffled = expected.shuffled()
        whenever(gitRepoRepository.getRepositories()).thenReturn(Observable.just(shuffled))

        val liveData = testObject.gitRepositories.test()

        liveData.assertValue(expected)
    }
}

fun <T> LiveData<T>.test(): TestObserver<T> {
    return TestObserver.test(this)
}
