package com.example.aula5.data.remote.responses

import com.example.aula5.data.remote.requests.Operation

data class GetOperationResponse(private val operations: MutableList<Operation>) {}