package com.example.githubusers.viewModel.state

import com.example.githubusers.model.models.User

data class UserListState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val error: String = "",
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
