package io.github.wilson.emmett.gitfinder

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Test
import retrofit2.Response


class GitRepositoryDtoRepositoryTest {

    private val githubApi = mock<GithubApi>()
    private val testObject = GitRepoRepository(githubApi)

    @Test
    fun getUsersDelegatesToGithubApi() {
        val list = listOf(RepositoryDto(), RepositoryDto())
        val success : Response<List<RepositoryDto>> =  Response.success(200, list)
        whenever(githubApi.reposForOrganization(any())).thenReturn(Observable.just(success))

        testObject.getRepositories("orgName").test()
            .assertValue(list)
            .assertComplete()

    }
}