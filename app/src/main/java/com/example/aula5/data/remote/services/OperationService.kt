package com.example.aula5.data.remote.services

import com.example.aula5.data.remote.requests.Operation
import com.example.aula5.data.remote.responses.GetOperationResponse
import com.example.aula5.data.remote.responses.PostOperationResponse
import retrofit2.Response
import retrofit2.http.*


interface OperationService {

    @POST("/operations")
    suspend fun postOperation(@Body body: Operation): Response<PostOperationResponse>

    @GET("/operations")
    suspend fun getOperation(): Response<GetOperationResponse>

}