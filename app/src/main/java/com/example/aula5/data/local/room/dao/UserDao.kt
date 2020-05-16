package com.example.aula5.data.local.room.dao

import androidx.room.*
import com.example.aula5.data.local.entities.User

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM user")
    suspend fun getAll() : MutableList<User>

    @Query("SELECT * FROM user WHERE login = :login")
    suspend fun getById(login: String) : User

    @Query("DELETE FROM user")
    suspend fun nukeTable()

}
