package io.github.wilson.emmett.gitfinder

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface GithubApi {
    @GET("users/{org}/repos")
    fun reposForOrganization(@Path("org") orgName : String): Observable<Response<List<RepositoryDto>>>
}