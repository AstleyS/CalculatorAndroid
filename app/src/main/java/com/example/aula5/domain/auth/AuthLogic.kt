package com.example.aula5.domain.auth

import android.util.Log
import com.example.aula5.data.local.entities.User
import com.example.aula5.data.local.room.dao.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.apache.commons.codec.digest.DigestUtils

class AuthLogic(private val storage: UserDao) {

    fun saveUser(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val hash: String = DigestUtils.sha256Hex(password)
            storage.insert(
                User(
                    email,
                    hash
                )
            )
        }
    }

}