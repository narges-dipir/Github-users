package com.example.githubusers.di

import android.content.Context
import androidx.room.Room
import com.example.githubusers.model.local.GithubUsersDao
import com.example.githubusers.model.local.GithubUsersDatabase
import com.example.githubusers.model.local.MIGRATIONS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideGithubUsersDatabase(@ApplicationContext context: Context): GithubUsersDatabase{
        return Room.databaseBuilder(
            context,
            GithubUsersDatabase::class.java,
            "blueApplication.db"
        ).addMigrations(*MIGRATIONS).build()
    }

    @Provides
    @Singleton
    fun provideTransactionTable(blueApplicationDatabase: GithubUsersDatabase): GithubUsersDao {
        return blueApplicationDatabase.githubUsersDao
    }
}