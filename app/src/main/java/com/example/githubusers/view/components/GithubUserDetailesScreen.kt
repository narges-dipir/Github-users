package com.example.githubusers.view.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.githubusers.R
import com.example.githubusers.model.models.User
import com.example.githubusers.viewModel.GithubUserDetailViewModel


@Composable
fun GithubUserDetailsScreen(
   // user: User? = null,
    nestedScrollConnection: NestedScrollConnection = rememberNestedScrollInteropConnection(),
    viewModel: GithubUserDetailViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val state = viewModel.state.value
    state.user?.let {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
            .systemBarsPadding(),
    ) {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
            ) {
                ProfileHeader(
                    scrollState,
                    it,
                    this@BoxWithConstraints.maxHeight,
                )
                GeneralInfo(
                    it,
                    this@BoxWithConstraints.maxHeight,
                )
            }
        }
    }
}
}

@Composable
private fun ProfileHeader(
    scrollState: ScrollState,
    data: User,
    containerHeight: Dp,
) {
    val offset = (scrollState.value / 2)
    val offsetDp = with(LocalDensity.current) { offset.toDp() }

    data.avatar_url?.let {
        AsyncImage(
            model = it,
            contentDescription = "avatar picture",
            modifier = Modifier
                .heightIn(max = containerHeight / 2)
                .fillMaxWidth()
                // TODO: Update to use offset to avoid recomposition
                .padding(
                    start = 16.dp,
                    top = offsetDp,
                    end = 16.dp,
                )
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.baseline_person_24),
        )
    }
}

@Composable
private fun GeneralInfo(
    user: User,
    containerHeight: Dp,
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = user.login,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            style = MaterialTheme.typography.bodyLarge,
        )
        Text(
            text = user.score.toString(),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 32.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Divider()
        Text(
            text = "user type: " + user.type,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Text(
            text = "organization: " + user.organizations_url,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
            text = "followers: " + user.followers_url,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
            text = "following: " + user.following_url,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height((containerHeight - 220.dp).coerceAtLeast(0.dp)))
    }
}

@Preview
@Composable
fun previewUserDetail() {
    val user = User(
        "torvalds",
        1024025,
        "MDQ6VXNlcjEwMjQwMjU=",
        "https://avatars.githubusercontent.com/u/1024025?v=4",
        "",
        "https://api.github.com/users/torvalds",
        "https://github.com/torvalds",
        "https://api.github.com/users/torvalds/followers",
        "https://api.github.com/users/torvalds/following{/other_user}",
        "https://api.github.com/users/torvalds/gists{/gist_id}",
        "https://api.github.com/users/torvalds/starred{/owner}{/repo}",
        "https://api.github.com/users/torvalds/subscriptions",
        "https://api.github.com/users/torvalds/orgs",
        "https://api.github.com/users/torvalds/repos",
        "https://api.github.com/users/torvalds/events{/privacy}",
        "https://api.github.com/users/torvalds/received_events",
        "User",
        false,
        1.0,
    )

}
