package com.example.aula5.ui.viewmodels

import android.app.Application
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.AndroidViewModel
import com.example.aula5.ui.activities.LoginActivity
import com.example.aula5.ui.activities.MainActivity
import com.example.aula5.ui.activities.RegisterActivity

class AuthViewModel(application: Application): AndroidViewModel(application) {

    fun onClickLogin(activity: FragmentActivity?) {
        val intent = Intent(activity, MainActivity::class.java)
        activity?.startActivity(intent)
    }

    fun onClickRegister(activity: FragmentActivity?) {
        val intent = Intent(activity, RegisterActivity::class.java)
        activity?.startActivity(intent)
    }

    fun onClickCancelRegister(activity: FragmentActivity?) {
        val intent = Intent(activity, LoginActivity::class.java)
        activity?.startActivity(intent)
    }

    fun onClickSubmitRegister(activity: FragmentActivity?) {
        val intent = Intent(activity, LoginActivity::class.java)
        activity?.startActivity(intent)
    }



}