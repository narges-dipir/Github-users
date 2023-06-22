package com.example.githubusers.model.models

data class GithubResponse(
    val incomplete_results: Boolean,
    val items: List<GithubUsersResponse>,
    val total_count: Int,
)
