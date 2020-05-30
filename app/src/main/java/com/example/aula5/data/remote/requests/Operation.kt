package com.example.aula5.data.remote.requests

import java.util.*


data class Operation(val expression: String, val result: Double) {
    private val uuid = UUID.randomUUID().toString()
}