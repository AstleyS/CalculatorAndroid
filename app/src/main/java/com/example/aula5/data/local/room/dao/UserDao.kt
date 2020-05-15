package com.example.aula5.data.local.room.dao

import androidx.room.*
import com.example.aula5.data.local.entities.User

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM user")
    suspend fun getAll() : MutableList<User>

    @Query("SELECT * FROM user WHERE uuid = :uuid")
    suspend fun getById(uuid: String) : User

}
