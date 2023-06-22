package com.example.githubusers.viewModel.state

sealed class UserListEvent {
    object Refresh : UserListEvent()
    data class OnSearchQueryChange(val query: String) : UserListEvent()
}
