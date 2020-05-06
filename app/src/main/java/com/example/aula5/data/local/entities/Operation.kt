package com.example.aula5.data.local.entities

import androidx.room.Entity
import java.util.*

@Entity
data class Operation(val expression: String, val result: Double) {

    var uuid: String = UUID.randomUUID().toString()

    override fun toString(): String {
        return "$expression=$result"
    }
}
