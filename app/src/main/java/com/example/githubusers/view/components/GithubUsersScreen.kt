package com.example.githubusers.view.components

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.githubusers.view.navigation.Navigation
import com.example.githubusers.view.state.UserListState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun GithubUsersScreen(
    navController: NavController,
) {
    val state: UserListState = UserListState()
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = false,
    )
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        OutlinedTextField(
            value = "ss",
            onValueChange = {},
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
                            .clickable {
                                navController.navigate(Navigation.GithubUserDetailScreen.route + "/${user.id}")
                            }
                            .padding(16.dp),
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

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}
