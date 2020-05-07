package com.example.aula5.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.aula5.data.local.entities.Operation

@Dao
interface OperationDao {

    @Insert
    suspend fun insert(operation: Operation)

    @Delete
    suspend fun removeOperation(operation: Operation)

    @Query("SELECT * FROM operation")
    suspend fun getAll() : MutableList<Operation>

    @Query("SELECT * FROM operation WHERE uuid = :uuid")
    suspend fun getById(uuid: String) : Operation

}