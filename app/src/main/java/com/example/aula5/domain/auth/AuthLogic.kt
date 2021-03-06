package com.example.aula5.domain.auth

import android.util.Log
import com.example.aula5.data.remote.requests.Login
import com.example.aula5.data.remote.services.AuthService
import com.example.aula5.ui.listeners.OnReceiveLoginAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

var token : String? = null

class AuthLogic(private val retrofit: Retrofit /*private val storage: UserDao*/) {

    fun authenticateUser(listener: OnReceiveLoginAuth?, email: String, password: String) {

        val service = retrofit.create(AuthService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val login = Login(email, password)
            val response = service.login(login)

            if (response.isSuccessful) {
                token = response.body()?.getToken
                listener?.onReceiveLoginAuth(true);
                Log.i(AuthLogic::class.java.simpleName, response.message())
            } else {
                listener?.onReceiveLoginAuth(false);
                Log.i(AuthLogic::class.java.simpleName, response.errorBody().toString())
            }
        }
    }

    /*
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

    fun deleteUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            storage.nukeTable()
        }
    }

     */

}