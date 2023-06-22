package com.example.githubusers.model.repository

import com.example.githubusers.model.local.GithubUsersDao
import com.example.githubusers.model.mappers.mapToEntity
import com.example.githubusers.model.mappers.mapToUser
import com.example.githubusers.model.models.User
import com.example.githubusers.model.remote.GithubUsersApi
import com.example.githubusers.utiles.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class GetGithubUsersRepositoryImpl constructor(
    private val transactionApi: GithubUsersApi,
    private val transactionsDao: GithubUsersDao,
) : IGetGithubUsersRepository {
    override suspend fun getAllUsers(): Flow<ResultWrapper<List<User>>> = flow {
        try {
            emit(ResultWrapper.Loading)
            if (transactionsDao.getAllUsers().isNotEmpty()) {
                emit(ResultWrapper.Success(transactionsDao.getAllUsers().map { it -> it.mapToUser() }))
            } else {
                transactionApi.getAllUsers().forEach {
                    saveUser(it.mapToUser())
                }
                emit(ResultWrapper.Success(transactionsDao.getAllUsers().map { it -> it.mapToUser() }))
            }
        } catch (e: HttpException) {
            emit(ResultWrapper.Error(e))
        }
    }

    override suspend fun saveUser(user: User) {
        transactionsDao.insertUser(user.mapToEntity())
    }
}
