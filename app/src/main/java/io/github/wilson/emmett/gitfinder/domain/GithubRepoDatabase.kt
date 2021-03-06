package io.github.wilson.emmett.gitfinder.domain

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [GitRepo::class]
)
abstract class GithubRepoDatabase : RoomDatabase() {

    abstract fun repoDao() : GitRepoDao
}
