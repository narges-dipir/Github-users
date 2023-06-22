package com.example.githubusers.model.remote

import com.example.githubusers.model.models.GithubUsersResponse
import retrofit2.http.GET

interface GithubUsersApi {

    @GET()
    suspend fun getAllUsers(): List<GithubUsersResponse>
}
