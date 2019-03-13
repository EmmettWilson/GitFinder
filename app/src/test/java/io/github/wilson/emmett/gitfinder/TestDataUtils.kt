package io.github.wilson.emmett.gitfinder

import io.github.wilson.emmett.gitfinder.domain.GitRepo
import io.github.wilson.emmett.gitfinder.githubService.OwnerDto
import io.github.wilson.emmett.gitfinder.githubService.RepositoryDto
import java.util.*

fun GitRepo.Companion.test() = GitRepo(
        random.nextLong(),
        "name".appendRandom(),
        "fullname".appendRandom(),
        "html_url".appendRandom(),
        random.nextInt(),
        "language".appendRandom(),
        "description".appendRandom())

fun RepositoryDto.Companion.test() = RepositoryDto(
        random.nextLong(),
        "name".appendRandom(),
        "description".appendRandom(),
        "full_name".appendRandom(),
        "html_url".appendRandom(),
        random.nextInt(),
        random.nextInt(),
        OwnerDto.test(),
        "language".appendRandom(),
        "type".appendRandom())

fun OwnerDto.Companion.test() = OwnerDto(
        "login".appendRandom(),
        random.nextLong(),
        "avatar_url".appendRandom(),
        "html_url".appendRandom(),
        "type".appendRandom())


private fun String.appendRandom() = this.plus(random.nextLong())
private val random = Random()



