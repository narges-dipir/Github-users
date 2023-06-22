package com.example.githubusers.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.githubusers.R
import com.example.githubusers.model.models.User
import com.example.githubusers.view.ui.theme.MelloDarkeWhite
import com.example.githubusers.view.ui.theme.MelloLightBlack

@Composable
fun GithubUserItem(
    modifier: Modifier = Modifier.background(color = Color.White),
    user: User,
) {
    Row(
        modifier =
        Modifier
            .fillMaxWidth()
            .clickable { },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize(),
        ) {
            Surface(
                modifier = Modifier
                    .width(70.dp)
                    .height(70.dp)
                    .padding(8.dp),
                shape = CircleShape,
                color = Color.White.copy(alpha = 0.1f),
            ) {
                AsyncImage(model = user.avatar_url,
                    contentDescription = "small avatar picture",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(10.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.baseline_person_24)
                    )
            }
        }
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .padding(2.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = user.login,
                modifier = Modifier.wrapContentWidth(),
                style = TextStyle(textDirection = TextDirection.ContentOrLtr),
                fontWeight = FontWeight.Bold,
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
            )
            Text(
                text = "type is: " + user.type,
                modifier = Modifier.wrapContentWidth(),
                style = TextStyle(textDirection = TextDirection.ContentOrLtr),
                color = if (isSystemInDarkTheme()) MelloDarkeWhite else MelloLightBlack,
                maxLines = 1,
            )
        }
    }
}

@Preview
@Composable
fun previewUserItem() {
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
    GithubUserItem(
        modifier = Modifier.fillMaxWidth(),
        user = user,
    )
}
