package com.example.githubusers.view.state

import com.example.githubusers.model.models.User

data class UserListState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val error: String = ""
)
