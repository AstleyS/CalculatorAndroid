package com.example.aula5.ui.viewmodels

import android.app.Application
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.AndroidViewModel
import com.example.aula5.data.local.room.UserDatabase
import com.example.aula5.data.remote.RetrofitBuilder
import com.example.aula5.domain.auth.AuthLogic
import com.example.aula5.ui.activities.LoginActivity
import com.example.aula5.ui.activities.MainActivity
import com.example.aula5.ui.activities.RegisterActivity
import com.example.aula5.ui.listeners.OnReceiveLoginAuth
import com.example.aula5.ui.listeners.OnReceiveToken

const val ENDPOINT = "https://cm-calculadora.herokuapp.com/api/"

class AuthViewModel(application: Application): AndroidViewModel(application) {

    private val storage = UserDatabase.getInstance(application).userDao()
    // private val authLogic = AuthLogic(storage)
    private val authLogic = AuthLogic(RetrofitBuilder.getInstance(ENDPOINT))
    private var listenerAuth: OnReceiveLoginAuth? = null
    private var listenerToken: OnReceiveToken? = null
    var auth = false;
    var token: String? = null

    fun onClickLogin(activity: FragmentActivity?, email: String, password: String) {
        authLogic.authenticateUser(listenerAuth, listenerToken, email, password)
        Thread.sleep(500)

        if (auth) {
            val intent = Intent(activity, MainActivity::class.java)
            intent.apply { putExtra(EXTRA_EMAIL, email) }
            intent.apply { putExtra(TOKEN, token) }
            activity?.startActivity(intent)
            activity?.finish()
        }
    }

    fun onClickRegister(activity: FragmentActivity?) {
        val intent = Intent(activity, RegisterActivity::class.java)
        activity?.startActivity(intent)
        activity?.finish()
    }

    fun onClickCancelRegister(activity: FragmentActivity?) {
        val intent = Intent(activity, LoginActivity::class.java)
        activity?.startActivity(intent)
        activity?.finish()
    }

    fun onClickSubmitRegister(activity: FragmentActivity?, email: String, password: String) {
        // authLogic.saveUser(email, password)
        val intent = Intent(activity, LoginActivity::class.java)
        activity?.startActivity(intent)
        activity?.finish()
    }

    fun registerListenerAuth(listener: OnReceiveLoginAuth) {
        this.listenerAuth = listener
        listener.onReceiveLoginAuth(auth)
    }
    fun registerListenerToken(listener: OnReceiveToken) {
        this.listenerToken = listener
        listener.onReceiveToken(token)
    }

    fun unregisterListener() {
        listenerAuth = null
    }

    fun unregisterListenerToken() {
        listenerToken = null
    }

    companion object {
        const val EXTRA_EMAIL = "Email"
        const val TOKEN = "Token"
    }


}