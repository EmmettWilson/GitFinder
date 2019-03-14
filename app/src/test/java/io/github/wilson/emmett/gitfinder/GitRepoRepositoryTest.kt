package io.github.wilson.emmett.gitfinder

import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.github.wilson.emmett.gitfinder.domain.GitRepo
import io.github.wilson.emmett.gitfinder.domain.GitRepoDao
import io.github.wilson.emmett.gitfinder.domain.GithubRepoDatabase
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GitRepoRepositoryTest{

    private val database =  mock<GithubRepoDatabase>()
    private val repoDao = mock<GitRepoDao>()

    private val testObject = GitRepoRepository(database)

    @Before
    fun setUp() {
        whenever(database.repoDao()).thenReturn(repoDao)
    }

    @Test
    fun getRepositoriesReturnsValuesFromDb() {
        val expected = MutableLiveData<List<GitRepo>>()
        whenever(repoDao.getRepos()).thenReturn(expected)

        val actual = testObject.getRepositories()

        assertEquals(expected, actual)
    }

}