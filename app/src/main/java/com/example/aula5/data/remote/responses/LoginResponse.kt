package com.example.aula5.data.remote.responses

import com.google.gson.annotations.SerializedName

// @SerializedName(token)
data class LoginResponse(private val email: String, private val token: String) {}