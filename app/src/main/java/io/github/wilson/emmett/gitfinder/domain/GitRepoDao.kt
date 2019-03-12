package io.github.wilson.emmett.gitfinder.domain

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GitRepoDao {

    @Insert
    fun insertRepos(gitRepos: List<GitRepo>)

    @Query("SELECT * FROM GitRepo")
    fun getRepos() : LiveData<List<GitRepo>>
}