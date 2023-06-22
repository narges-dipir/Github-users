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
import javax.inject.Inject

class GetGithubUsersRepositoryImpl @Inject constructor(
    private val githubUsersApi: GithubUsersApi,
    private val githubUsersDao: GithubUsersDao,
) : IGetGithubUsersRepository {
    override suspend fun getAllUsers(): Flow<ResultWrapper<List<User>>> = flow {
        try {
            emit(ResultWrapper.Loading)
            if (githubUsersDao.getAllUsers().isNotEmpty()) {
                emit(
                    ResultWrapper.Success(
                        githubUsersDao.getAllUsers().map { it -> it.mapToUser() })
                )
            } else {
                githubUsersApi.getAllUsers().items.forEach {
                    saveUser(it.mapToUser())
                }
                emit(
                    ResultWrapper.Success(
                        githubUsersDao.getAllUsers().map { it -> it.mapToUser() })
                )
            }
        } catch (e: HttpException) {
            emit(ResultWrapper.Error(e))
        }
    }

    override suspend fun saveUser(user: User) {
        githubUsersDao.insertUser(user.mapToEntity())
    }

    override suspend fun getUserById(id: Int): Flow<ResultWrapper<User>> = flow {
        try {
            emit(ResultWrapper.Loading)
            val result = githubUsersDao.getUserById(id)
            emit(ResultWrapper.Success(result.mapToUser()))
        } catch (e: HttpException) {
            emit(ResultWrapper.Error(e))
        }
    }

    override suspend fun searchUser(query: String): Flow<ResultWrapper<User>> = flow {
        try {
            emit(ResultWrapper.Loading)
            val result = githubUsersDao.searchUser(query)
            if (result!= null)
            emit(ResultWrapper.Success(result.mapToUser()))
        } catch (e: HttpException) {
            emit(ResultWrapper.Error(e))
        }
    }


}
