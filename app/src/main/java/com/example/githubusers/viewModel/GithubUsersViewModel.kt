package com.example.githubusers.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubusers.model.repository.IGetGithubUsersRepository
import com.example.githubusers.utiles.ResultWrapper
import com.example.githubusers.viewModel.state.UserListEvent
import com.example.githubusers.viewModel.state.UserListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubUsersViewModel @Inject constructor(
    private val getGithubUsersRepository: IGetGithubUsersRepository,
) : ViewModel() {
    var state by mutableStateOf(UserListState())
    private var searchJob: Job? = null
    init {
        getGithubUsers()
    }
    fun onEvent(event: UserListEvent) {
        when (event) {
            is UserListEvent.OnSearchQueryChange -> querySearch(event.query)
            UserListEvent.Refresh -> {
                getGithubUsers()
            }
        }
    }

    private fun getGithubUsers() {
        viewModelScope.launch {
            getGithubUsersRepository.getAllUsers().collect { result ->
                when (result) {
                    is ResultWrapper.Success -> {
                        result.data?.let { users ->
                            state = state.copy(
                                users = users,
                            )
                        }
                    }
                    is ResultWrapper.Error -> Unit
                    is ResultWrapper.Loading -> {
                        state = state.copy(isLoading = true)
                    }
                }
            }
        }
    }
    private fun querySearch(
        query: String,
    ) {
    }
}
