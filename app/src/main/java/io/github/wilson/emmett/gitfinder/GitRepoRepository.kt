package io.github.wilson.emmett.gitfinder

import io.github.wilson.emmett.gitfinder.githubService.GithubApi
import io.github.wilson.emmett.gitfinder.githubService.RepositoryDto
import io.reactivex.Observable

class GitRepoRepository(private val githubApi: GithubApi) {
    fun getRepositories(organizationName: String): Observable<List<RepositoryDto>> {
        return githubApi.reposForOrganization(organizationName)
            .map { it.body() }
    }

}
