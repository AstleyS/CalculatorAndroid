package com.example.aula5.data.repositories

import com.example.aula5.data.local.room.dao.OperationDao
import retrofit2.Retrofit

class OperationRepository(private val local: OperationDao, private val remote: Retrofit) {
}