package io.github.wilson.emmett.gitfinder

import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.github.wilson.emmett.gitfinder.domain.GitRepo
import io.github.wilson.emmett.gitfinder.githubService.RepositoryDto
import io.reactivex.schedulers.TestScheduler
import junit.framework.Assert.assertEquals
import org.junit.Test

class GitRepoViewModelTest{
    private val gitRepoRepository = mock<GitRepoRepository>()

    private val testObject = GitRepoViewModel(gitRepoRepository)

    @Test
    fun getReposDelegatesToRepository() {
        val mutableLiveData = MutableLiveData<List<GitRepo>>()
        whenever(gitRepoRepository.getRepositories()).thenReturn(mutableLiveData)

        val actual = testObject.getRepositories()

        assertEquals(mutableLiveData, actual)
    }
}