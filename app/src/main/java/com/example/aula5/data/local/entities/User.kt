package com.example.aula5.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class User(@PrimaryKey val login: String, val password: String) {

    override fun toString(): String {
        return "Login $login; Password = $password"
    }
}