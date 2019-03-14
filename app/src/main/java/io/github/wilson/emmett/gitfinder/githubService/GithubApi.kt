package io.github.wilson.emmett.gitfinder.githubService

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface GithubApi {
    @GET("users/{org}/repos")
    fun reposForOrganization(@Path("org") orgName : String): Observable<List<RepositoryDto>>
}