package com.example.aula5.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.aula5.R
import com.example.aula5.ui.utils.NavigationManager
import kotlinx.android.synthetic.main.activity_main.*

class AuthActivity : AppCompatActivity() {

    private val TAG = AuthActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "o método onCreate foi invocado")
        setContentView(R.layout.activity_auth)
        setSupportActionBar(toolbar)
        NavigationManager.goToLoginFragment(supportFragmentManager)

    }
}
