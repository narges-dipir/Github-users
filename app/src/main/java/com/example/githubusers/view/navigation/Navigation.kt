package com.example.githubusers.view.navigation

sealed class Navigation(val route: String) {
    object GithubUsersScreen: Navigation("github_users_screen")
    object GithubUserDetailScreen: Navigation("github_user_detail_screen")
}