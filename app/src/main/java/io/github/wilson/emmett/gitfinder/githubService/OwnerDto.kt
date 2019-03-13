package io.github.wilson.emmett.gitfinder.githubService

import androidx.annotation.Keep

@Keep
data class OwnerDto (
    private val login : String,
    private val id : Long,
    private val avatar_url : String,
    private val html_url : String,
    private val type : String
){
    companion object
}

//[
//{
//    "id": 33407486,
//    "node_id": "MDEwOlJlcG9zaXRvcnkzMzQwNzQ4Ng==",
//    "name": "Koin",
//    "full_name": "Koin/Koin",
//    "private": false,
//    "owner": {
//    "login": "Koin",
//    "id": 11797555,
//    "node_id": "MDQ6VXNlcjExNzk3NTU1",
//    "avatar_url": "https://avatars0.githubusercontent.com/u/11797555?v=4",
//    "gravatar_id": "",
//    "url": "https://api.github.com/users/Koin",
//    "html_url": "https://github.com/Koin",
//    "followers_url": "https://api.github.com/users/Koin/followers",
//    "following_url": "https://api.github.com/users/Koin/following{/other_user}",
//    "gists_url": "https://api.github.com/users/Koin/gists{/gist_id}",
//    "starred_url": "https://api.github.com/users/Koin/starred{/owner}{/repo}",
//    "subscriptions_url": "https://api.github.com/users/Koin/subscriptions",
//    "organizations_url": "https://api.github.com/users/Koin/orgs",
//    "repos_url": "https://api.github.com/users/Koin/repos",
//    "events_url": "https://api.github.com/users/Koin/events{/privacy}",
//    "received_events_url": "https://api.github.com/users/Koin/received_events",
//    "type": "User",
//    "site_admin": false
//},
//    "html_url": "https://github.com/Koin/Koin",
//    "description": null,
//    "fork": false,
//    "url": "https://api.github.com/repos/Koin/Koin",
//    "forks_url": "https://api.github.com/repos/Koin/Koin/forks",
//    "keys_url": "https://api.github.com/repos/Koin/Koin/keys{/key_id}",
//    "collaborators_url": "https://api.github.com/repos/Koin/Koin/collaborators{/collaborator}",
//    "teams_url": "https://api.github.com/repos/Koin/Koin/teams",
//    "hooks_url": "https://api.github.com/repos/Koin/Koin/hooks",
//    "issue_events_url": "https://api.github.com/repos/Koin/Koin/issues/events{/number}",
//    "events_url": "https://api.github.com/repos/Koin/Koin/events",
//    "assignees_url": "https://api.github.com/repos/Koin/Koin/assignees{/user}",
//    "branches_url": "https://api.github.com/repos/Koin/Koin/branches{/branch}",
//    "tags_url": "https://api.github.com/repos/Koin/Koin/tags",
//    "blobs_url": "https://api.github.com/repos/Koin/Koin/git/blobs{/sha}",
//    "git_tags_url": "https://api.github.com/repos/Koin/Koin/git/tags{/sha}",
//    "git_refs_url": "https://api.github.com/repos/Koin/Koin/git/refs{/sha}",
//    "trees_url": "https://api.github.com/repos/Koin/Koin/git/trees{/sha}",
//    "statuses_url": "https://api.github.com/repos/Koin/Koin/statuses/{sha}",
//    "languages_url": "https://api.github.com/repos/Koin/Koin/languages",
//    "stargazers_url": "https://api.github.com/repos/Koin/Koin/stargazers",
//    "contributors_url": "https://api.github.com/repos/Koin/Koin/contributors",
//    "subscribers_url": "https://api.github.com/repos/Koin/Koin/subscribers",
//    "subscription_url": "https://api.github.com/repos/Koin/Koin/subscription",
//    "commits_url": "https://api.github.com/repos/Koin/Koin/commits{/sha}",
//    "git_commits_url": "https://api.github.com/repos/Koin/Koin/git/commits{/sha}",
//    "comments_url": "https://api.github.com/repos/Koin/Koin/comments{/number}",
//    "issue_comment_url": "https://api.github.com/repos/Koin/Koin/issues/comments{/number}",
//    "contents_url": "https://api.github.com/repos/Koin/Koin/contents/{+path}",
//    "compare_url": "https://api.github.com/repos/Koin/Koin/compare/{base}...{head}",
//    "merges_url": "https://api.github.com/repos/Koin/Koin/merges",
//    "archive_url": "https://api.github.com/repos/Koin/Koin/{archive_format}{/ref}",
//    "downloads_url": "https://api.github.com/repos/Koin/Koin/downloads",
//    "issues_url": "https://api.github.com/repos/Koin/Koin/issues{/number}",
//    "pulls_url": "https://api.github.com/repos/Koin/Koin/pulls{/number}",
//    "milestones_url": "https://api.github.com/repos/Koin/Koin/milestones{/number}",
//    "notifications_url": "https://api.github.com/repos/Koin/Koin/notifications{?since,all,participating}",
//    "labels_url": "https://api.github.com/repos/Koin/Koin/labels{/name}",
//    "releases_url": "https://api.github.com/repos/Koin/Koin/releases{/id}",
//    "deployments_url": "https://api.github.com/repos/Koin/Koin/deployments",
//    "created_at": "2015-04-04T14:06:04Z",
//    "updated_at": "2015-04-04T14:06:04Z",
//    "pushed_at": "2015-04-04T14:06:04Z",
//    "git_url": "git://github.com/Koin/Koin.git",
//    "ssh_url": "git@github.com:Koin/Koin.git",
//    "clone_url": "https://github.com/Koin/Koin.git",
//    "svn_url": "https://github.com/Koin/Koin",
//    "homepage": null,
//    "size": 0,
//    "stargazers_count": 0,
//    "watchers_count": 0,
//    "language": null,
//    "has_issues": true,
//    "has_projects": true,
//    "has_downloads": true,
//    "has_wiki": true,
//    "has_pages": false,
//    "forks_count": 0,
//    "mirror_url": null,
//    "archived": false,
//    "open_issues_count": 0,
//    "license": null,
//    "forks": 0,
//    "open_issues": 0,
//    "watchers": 0,
//    "default_branch": "master"
//}
//]
