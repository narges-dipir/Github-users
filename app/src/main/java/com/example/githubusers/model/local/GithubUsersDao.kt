package com.example.githubusers.model.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubusers.model.local.entities.UserEntity

@Dao
interface GithubUsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(transaction: UserEntity)

    @Query("SELECT * FROM UserTable ")
    suspend fun getAllUsers(): List<UserEntity>

    @Delete
    suspend fun deleteUser(transaction: UserEntity)
}
