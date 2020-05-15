package com.example.aula5.ui.viewmodels

import android.app.Application
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.AndroidViewModel
import com.example.aula5.data.local.room.UserDatabase
import com.example.aula5.domain.auth.AuthLogic
import com.example.aula5.ui.activities.LoginActivity
import com.example.aula5.ui.activities.MainActivity
import com.example.aula5.ui.activities.RegisterActivity

class AuthViewModel(application: Application): AndroidViewModel(application) {

    private val storage = UserDatabase.getInstance(application).userDao()
    private val authLogic = AuthLogic(storage)

    fun onClickLogin(activity: FragmentActivity?, email: String) {
        val intent = Intent(activity, MainActivity::class.java)
        intent.apply { putExtra(EXTRA_EMAIL, email) }
        activity?.startActivity(intent)
        activity?.finish()
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




    companion object {
        const val EXTRA_EMAIL = "Email"
    }


}