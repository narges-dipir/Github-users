package com.example.githubusers.viewModel.state

import com.example.githubusers.model.models.User

data class UserDetailState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: String = ""
)