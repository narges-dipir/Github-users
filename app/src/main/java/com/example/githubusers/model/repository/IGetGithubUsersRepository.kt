package com.example.githubusers.model.repository

import com.example.githubusers.model.models.User
import com.example.githubusers.utiles.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface IGetGithubUsersRepository {
    suspend fun getAllUsers(): Flow<ResultWrapper<List<User>>>
    suspend fun saveUser(user: User)
    suspend fun getUserById(id: Int): Flow<ResultWrapper<User>>
    suspend fun searchUser(query: String): Flow<ResultWrapper<User>>
}
