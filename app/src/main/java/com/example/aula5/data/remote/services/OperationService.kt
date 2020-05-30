package com.example.aula5.data.remote.services

import com.example.aula5.data.remote.requests.Operation
import com.example.aula5.data.remote.responses.GetOperationResponse
import com.example.aula5.data.remote.responses.PostOperationResponse
import retrofit2.Response
import retrofit2.http.*


interface OperationService {

    @POST("/operations")
    suspend fun postOperation(@Header ("Authorization") authorization : String?, @Body body: Operation): Response<PostOperationResponse>

    @GET("/operations")
    suspend fun getOperation(@Header ("Authorization") authorization: String?): Response<MutableList<Operation>>

    @DELETE("/operations")
    suspend fun deleteOperations(@Header ("Authorization") authorization: String?): Response<MutableList<Operation>>

}