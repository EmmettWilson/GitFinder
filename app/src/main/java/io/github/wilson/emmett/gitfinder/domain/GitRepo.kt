package io.github.wilson.emmett.gitfinder.domain

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.wilson.emmett.gitfinder.githubService.RepositoryDto

@Keep
@Entity
data class GitRepo(
    @PrimaryKey var id: Long,
    var name: String,
    var full_name: String,
    var html_url: String,
    var watchers: Int,
    var language: String,
    var description: String
) {

    companion object {
        //TODO Fetch these from resources for locale support
        fun from(dto: RepositoryDto): GitRepo {
            return GitRepo(
                dto.id,
                dto.name,
                dto.full_name,
                dto.html_url,
                dto.watchers,
                dto.language?:"Unknown",
                dto.description?:"No description provided"
            )
        }
    }
}


//{
//    "id": 93515203,
//    "node_id": "MDEwOlJlcG9zaXRvcnk5MzUxNTIwMw==",
//    "name": "koin",
//    "full_name": "InsertKoinIO/koin",
//    "private": false,
//    "forks": 169,
//    "open_issues": 44,
//    "watchers": 3163,
//    "default_branch": "master"
//    "html_url": "https://github.com/InsertKoinIO/koin",
//    "description": "KOIN - a pragmatic lightweight dependency injection framework for Kotlin",
//    "fork": false,
//    "url": "https://api.github.com/repos/InsertKoinIO/koin",
//    "forks_url": "https://api.github.com/repos/InsertKoinIO/koin/forks",
//    "keys_url": "https://api.github.com/repos/InsertKoinIO/koin/keys{/key_id}",
//    "collaborators_url": "https://api.github.com/repos/InsertKoinIO/koin/collaborators{/collaborator}",
//    "teams_url": "https://api.github.com/repos/InsertKoinIO/koin/teams",
//    "hooks_url": "https://api.github.com/repos/InsertKoinIO/koin/hooks",
//    "issue_events_url": "https://api.github.com/repos/InsertKoinIO/koin/issues/events{/number}",
//    "events_url": "https://api.github.com/repos/InsertKoinIO/koin/events",
//    "assignees_url": "https://api.github.com/repos/InsertKoinIO/koin/assignees{/user}",
//    "branches_url": "https://api.github.com/repos/InsertKoinIO/koin/branches{/branch}",
//    "tags_url": "https://api.github.com/repos/InsertKoinIO/koin/tags",
//    "blobs_url": "https://api.github.com/repos/InsertKoinIO/koin/git/blobs{/sha}",
//    "git_tags_url": "https://api.github.com/repos/InsertKoinIO/koin/git/tags{/sha}",
//    "git_refs_url": "https://api.github.com/repos/InsertKoinIO/koin/git/refs{/sha}",
//    "trees_url": "https://api.github.com/repos/InsertKoinIO/koin/git/trees{/sha}",
//    "statuses_url": "https://api.github.com/repos/InsertKoinIO/koin/statuses/{sha}",
//    "languages_url": "https://api.github.com/repos/InsertKoinIO/koin/languages",
//    "stargazers_url": "https://api.github.com/repos/InsertKoinIO/koin/stargazers",
//    "contributors_url": "https://api.github.com/repos/InsertKoinIO/koin/contributors",
//    "subscribers_url": "https://api.github.com/repos/InsertKoinIO/koin/subscribers",
//    "subscription_url": "https://api.github.com/repos/InsertKoinIO/koin/subscription",
//    "commits_url": "https://api.github.com/repos/InsertKoinIO/koin/commits{/sha}",
//    "git_commits_url": "https://api.github.com/repos/InsertKoinIO/koin/git/commits{/sha}",
//    "comments_url": "https://api.github.com/repos/InsertKoinIO/koin/comments{/number}",
//    "issue_comment_url": "https://api.github.com/repos/InsertKoinIO/koin/issues/comments{/number}",
//    "contents_url": "https://api.github.com/repos/InsertKoinIO/koin/contents/{+path}",
//    "compare_url": "https://api.github.com/repos/InsertKoinIO/koin/compare/{base}...{head}",
//    "merges_url": "https://api.github.com/repos/InsertKoinIO/koin/merges",
//    "archive_url": "https://api.github.com/repos/InsertKoinIO/koin/{archive_format}{/ref}",
//    "downloads_url": "https://api.github.com/repos/InsertKoinIO/koin/downloads",
//    "issues_url": "https://api.github.com/repos/InsertKoinIO/koin/issues{/number}",
//    "pulls_url": "https://api.github.com/repos/InsertKoinIO/koin/pulls{/number}",
//    "milestones_url": "https://api.github.com/repos/InsertKoinIO/koin/milestones{/number}",
//    "notifications_url": "https://api.github.com/repos/InsertKoinIO/koin/notifications{?since,all,participating}",
//    "labels_url": "https://api.github.com/repos/InsertKoinIO/koin/labels{/name}",
//    "releases_url": "https://api.github.com/repos/InsertKoinIO/koin/releases{/id}",
//    "deployments_url": "https://api.github.com/repos/InsertKoinIO/koin/deployments",
//    "created_at": "2017-06-06T12:21:06Z",
//    "updated_at": "2019-03-12T03:07:00Z",
//    "pushed_at": "2019-02-26T08:57:33Z",
//    "git_url": "git://github.com/InsertKoinIO/koin.git",
//    "ssh_url": "git@github.com:InsertKoinIO/koin.git",
//    "clone_url": "https://github.com/InsertKoinIO/koin.git",
//    "svn_url": "https://github.com/InsertKoinIO/koin",
//    "homepage": "https://insert-koin.io/",
//    "size": 37458,
//    "stargazers_count": 3163,
//    "watchers_count": 3163,
//    "language": "Kotlin",
//    "has_issues": true,
//    "has_projects": true,
//    "has_downloads": true,
//    "has_wiki": true,
//    "has_pages": false,
//    "forks_count": 169,
//    "mirror_url": null,
//    "archived": false,
//    "open_issues_count": 44,
//    "license": {
//    "key": "apache-2.0",
//    "name": "Apache License 2.0",
//    "spdx_id": "Apache-2.0",
//    "url": "https://api.github.com/licenses/apache-2.0",
//    "node_id": "MDc6TGljZW5zZTI="
//    "owner": {
//    "login": "InsertKoinIO",
//    "id": 38280958,
//    "node_id": "MDEyOk9yZ2FuaXphdGlvbjM4MjgwOTU4",
//    "avatar_url": "https://avatars1.githubusercontent.com/u/38280958?v=4",
//    "gravatar_id": "",
//    "url": "https://api.github.com/users/InsertKoinIO",
//    "html_url": "https://github.com/InsertKoinIO",
//    "followers_url": "https://api.github.com/users/InsertKoinIO/followers",
//    "following_url": "https://api.github.com/users/InsertKoinIO/following{/other_user}",
//    "gists_url": "https://api.github.com/users/InsertKoinIO/gists{/gist_id}",
//    "starred_url": "https://api.github.com/users/InsertKoinIO/starred{/owner}{/repo}",
//    "subscriptions_url": "https://api.github.com/users/InsertKoinIO/subscriptions",
//    "organizations_url": "https://api.github.com/users/InsertKoinIO/orgs",
//    "repos_url": "https://api.github.com/users/InsertKoinIO/repos",
//    "events_url": "https://api.github.com/users/InsertKoinIO/events{/privacy}",
//    "received_events_url": "https://api.github.com/users/InsertKoinIO/received_events",
//    "type": "Organization",
//    "site_admin": false
//},
//},
//}