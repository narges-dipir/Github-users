package com.example.githubusers.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubusers.model.repository.IGetGithubUsersRepository
import com.example.githubusers.utiles.Constants
import com.example.githubusers.utiles.ResultWrapper
import com.example.githubusers.viewModel.state.UserDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubUserDetailViewModel @Inject constructor(
    private val getGithubUsersRepository: IGetGithubUsersRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(UserDetailState())
    val state: State<UserDetailState> = _state

init {
    savedStateHandle.get<String>(Constants.PARAM_USER_ID)?.let {userId ->
        getUserById(userId)
    }
}
    private fun getUserById(id: String) {
        viewModelScope.launch {
            getGithubUsersRepository.getUserById(id.toInt()).collect { result ->
                when (result) {
                    is ResultWrapper.Error -> {
                        _state.value = UserDetailState(
                            error = result.exception.message ?: "An unexpected error occured"
                        )
                    }
                    ResultWrapper.Loading -> {
                        _state.value = UserDetailState(isLoading = true)
                    }
                    is ResultWrapper.Success -> {
                        _state.value = UserDetailState(user = result.data)
                    }
                }

            }
        }
    }
}
