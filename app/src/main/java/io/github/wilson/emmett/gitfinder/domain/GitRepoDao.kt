package io.github.wilson.emmett.gitfinder.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Observable

@Dao
interface GitRepoDao {

    @Insert
    fun insertRepos(gitRepos: List<GitRepo>)

    @Query("SELECT * FROM GitRepo")
    fun getRepos() : Observable<List<GitRepo>>
}