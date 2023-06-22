package com.example.githubusers.model.models

data class GithubResponse(
    val incomplete_results: Boolean,
    val items: List<GithubUserResponse>,
    val total_count: Int,
)
