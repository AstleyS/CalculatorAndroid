package com.example.aula5.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.aula5.R
import com.example.aula5.ui.utils.NavigationManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_calculator.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val TAG = MainActivity::class.java.simpleName
    private val VISOR_KEY = "visor"
    private val HISTORIC_KEY = "historic"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "o método onCreate foi invocado")
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setupDrawerMenu()
        NavigationManager.gotToCalculatorFragment(supportFragmentManager)
    }

    override fun onDestroy() {
        Log.i(TAG, "O método onDestroy foi invocado")
        super.onDestroy()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        text_visor.text = savedInstanceState.getString(VISOR_KEY)
        //historic?.text = savedInstanceState.getString(HISTORIC_KEY)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString(VISOR_KEY, text_visor.text.toString())
            //putString(HISTORIC_KEY, historic?.text.toString())
        }
        super.onSaveInstanceState(outState)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.nav_calulator -> NavigationManager.gotToCalculatorFragment(supportFragmentManager)
            R.id.nav_history, R.id.button_list_historic -> NavigationManager.gotToHistoryFragment(supportFragmentManager)
            R.id.nav_logout -> finish()
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun screenRotated(savedInstanceState: Bundle?): Boolean {
        return savedInstanceState != null
    }

//on long click, interface com ... ao metodo

    private fun setupDrawerMenu() {
        val toogle = ActionBarDrawerToggle(this, drawer, toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        )
        nav_drawer.setNavigationItemSelectedListener(this)
        drawer.addDrawerListener(toogle)
        toogle.syncState()
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) drawer.closeDrawer(GravityCompat.START)
        else if (supportFragmentManager.backStackEntryCount == 1) finish()
        else super.onBackPressed()
    }


}