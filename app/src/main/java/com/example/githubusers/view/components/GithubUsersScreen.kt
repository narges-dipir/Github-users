package com.example.githubusers.view.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.githubusers.view.navigation.Navigation
import com.example.githubusers.viewModel.GithubUsersViewModel
import com.example.githubusers.viewModel.state.UserListEvent
import com.example.githubusers.viewModel.state.UserListState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun GithubUsersScreen(
    navController: NavController,
    viewModel: GithubUsersViewModel = hiltViewModel()
) {
    val state: UserListState = viewModel.state
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isRefreshing,
    )
    var loadingVisibility by remember { mutableStateOf(viewModel.state.isLoading) }
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {

        AnimatedVisibility(
            visible = loadingVisibility,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = {
                viewModel.onEvent(
                    UserListEvent.OnSearchQueryChange(it)
                )
                if (it == "")
                    viewModel.onEvent(UserListEvent.Refresh)
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Search...")
            },
            maxLines = 1,
            singleLine = true,
        )
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                viewModel.onEvent(UserListEvent.Refresh)
            },
        ) {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                items(state.users.size) { i ->
                    val user = state.users[i]
                    GithubUserItem(
                        user = user,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {}
                            .padding(16.dp),
                        onItemClick = {
                            navController.navigate(Navigation.GithubUserDetailsScreen.route + "/${user.id}")
                        }
                    )
                    if (i < state.users.size) {
                        Divider(
                            modifier = Modifier.padding(
                                horizontal = 16.dp,
                            ),
                        )
                    }
                    if (state.error.isNotBlank()) {
                        Text(
                            text = state.error,
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                                .align(Alignment.CenterHorizontally),
                        )
                    }
                }
            }
        }
    }

}

