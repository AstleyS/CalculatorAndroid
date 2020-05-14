package com.example.aula5.ui.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.aula5.R
import com.example.aula5.ui.fragments.CalculatorFragment
import com.example.aula5.ui.fragments.HistoryFragment
import com.example.aula5.ui.fragments.LoginFragment
import com.example.aula5.ui.fragments.RegisterFragment

abstract class NavigationManager {

    companion object {

        private fun placeFragment(fm: FragmentManager, fragment: Fragment) {
            val transition = fm.beginTransaction()
            transition.replace(R.id.frame, fragment)
            transition.addToBackStack(null)
            transition.commit()
        }

        fun gotToCalculatorFragment(fm: FragmentManager) {
            placeFragment(
                fm,
                CalculatorFragment()
            )
        }

        fun gotToHistoryFragment(fm: FragmentManager) {
            placeFragment(
                fm,
                HistoryFragment()
            )
        }

        fun goToLoginFragment(fm: FragmentManager) {
            placeFragment(
                fm,
                LoginFragment()
            )
        }

        fun goToRegisterFragment(fm: FragmentManager) {
            placeFragment(
                fm,
                RegisterFragment()
            )
        }


    }

}
