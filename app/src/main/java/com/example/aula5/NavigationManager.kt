package com.example.aula5

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

abstract class NavigationManager {

    companion object {

        private fun placeFragment(fm: FragmentManager, fragment: Fragment) {
            val transition = fm.beginTransaction()
            transition.replace(R.id.frame, fragment)
            transition.addToBackStack(null)
            transition.commit()
        }

        fun gotToCalculatorFragment(fm: FragmentManager) {
            placeFragment(fm, CalculatorFragment())
        }

        fun gotToHistoryFragment(fm: FragmentManager) {
            placeFragment(fm, HistoryFragment())
        }


    }

}
