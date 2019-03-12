package io.github.wilson.emmett.gitfinder

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.github.wilson.emmett.gitfinder.githubService.RepositoryDto
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Test

class GitRepositoryDtoViewModelTest{
    private val mainThreadScheduler = TestScheduler()
    private val gitRepoRepository = mock<GitRepoRepository>()

    private val testObject = GitRepoViewModel(gitRepoRepository, mainThreadScheduler)

    @Test
    fun getReposReturnsValueOnMainThread() {
        val expected = listOf(mock<RepositoryDto>())
        whenever(gitRepoRepository.getRepositories(any())).thenReturn(Observable.just(expected))

        val testObserver = testObject.getRepositories().test()

        testObserver.assertNoValues()
            .assertNotComplete()

        mainThreadScheduler.triggerActions()

        testObserver.assertValue(expected)
    }
}