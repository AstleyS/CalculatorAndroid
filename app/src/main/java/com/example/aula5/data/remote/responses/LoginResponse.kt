package com.example.aula5.data.remote.responses


// @SerializedName(token)
data class LoginResponse(private val email: String, private val token: String) {
    val getToken get() = token
}