package com.example.aula5

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_calculator.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName
    private val VISOR_KEY = "visor"
    private val HISTORIC_KEY = "historic"
    private val LIST_HISTORIC_KEY = "list_historic"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "o método onCreate foi invocado")
        setContentView(R.layout.activity_main)
        NavigationManager.gotToCalculatorFragment(supportFragmentManager)
    }

    override fun onDestroy() {
        Log.i(TAG, "O método onDestroy foi invocado")
        super.onDestroy()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        text_visor.text = savedInstanceState.getString(VISOR_KEY)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run { putString(VISOR_KEY, text_visor.text.toString())}
        super.onSaveInstanceState(outState)
    }

}