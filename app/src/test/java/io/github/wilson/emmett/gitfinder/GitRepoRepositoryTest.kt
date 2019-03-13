package io.github.wilson.emmett.gitfinder

import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.*
import io.github.wilson.emmett.gitfinder.domain.GitRepo
import io.github.wilson.emmett.gitfinder.domain.GitRepoDao
import io.github.wilson.emmett.gitfinder.domain.GithubRepoDatabase
import io.github.wilson.emmett.gitfinder.githubService.GithubApi
import io.github.wilson.emmett.gitfinder.githubService.RepositoryDto
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class GitRepoRepositoryTest{

    private val githubApi = mock<GithubApi>()
    private val database =  mock<GithubRepoDatabase>()
    private val repoDao = mock<GitRepoDao>()

    private val testObject = GitRepoRepository(githubApi, database)

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