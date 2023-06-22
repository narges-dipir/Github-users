package com.example.githubusers.view.navigation

sealed class Navigation(val route: String) {
    object GithubUsersScreen: Navigation("github_users_screen")
    object GithubUserDetailsScreen: Navigation("github_user_details_screen")
}