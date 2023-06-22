package com.example.githubusers.di

import com.example.githubusers.model.repository.GetGithubUsersRepositoryImpl
import com.example.githubusers.model.repository.IGetGithubUsersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindGithubUsersRepository(
        geGithubUsersRepositoryImpl: GetGithubUsersRepositoryImpl,
    ): IGetGithubUsersRepository
}
