package com.example.githubusers.model.remote

import com.example.githubusers.model.models.GithubResponse
import com.example.githubusers.model.models.GithubUserResponse
import retrofit2.http.GET

interface GithubUsersApi {

    @GET("/search/users?q=type%3Aall")
    suspend fun getAllUsers(): GithubResponse

}
