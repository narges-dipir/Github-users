package com.example.githubusers.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.githubusers.view.components.GithubUserDetailsScreen
import com.example.githubusers.view.components.GithubUsersScreen
import com.example.githubusers.view.navigation.Navigation
import com.example.githubusers.view.ui.theme.GithubUsersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubUsersTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Navigation.GithubUsersScreen.route,
                    ) {
                        composable(
                            route = Navigation.GithubUsersScreen.route,
                        ) {
                            GithubUsersScreen(navController = navController)
                        }
                        composable(
                            route = Navigation.GithubUserDetailsScreen.route + "/{id}",
                        ) {
                            GithubUserDetailsScreen()
                        }
                    }
                }
            }
        }
    }
}
