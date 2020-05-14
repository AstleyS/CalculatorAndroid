package com.example.aula5.data.remote.services

import com.example.aula5.data.remote.requests.Login
import com.example.aula5.data.remote.responses.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("users/login")
    suspend fun login(@Body body: Login): Response<LoginResponse>
}