package com.example.aula5.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aula5.ui.utils.NavigationManager
import com.example.aula5.R

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        NavigationManager.gotToHistoryFragment(
            supportFragmentManager
        )
    }
}
