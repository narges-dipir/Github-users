package com.example.githubusers.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubusers.model.local.entities.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
)
abstract class GithubUsersDatabase : RoomDatabase() {
    abstract val githubUsersDao: GithubUsersDao
}
