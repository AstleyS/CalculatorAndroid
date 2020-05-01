package com.example.aula5.viewModel

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.example.aula5.NavigationManager
import com.example.aula5.domain.CalculatorLogic


class CalculatorViewModel: ViewModel() {

    private val calculatorLogic = CalculatorLogic()
    var display: String = "0"

    fun onClickSymbol(symbol:String): String {
        display = calculatorLogic.insertSymbol(display, symbol)
        return display
    }

    fun onClickEquals(): String {
        val result = calculatorLogic.performeOperation(display)
        display = result.toString()
        return display
    }

    fun onClickHistory(supportManager: FragmentManager) {
        NavigationManager.gotToHistoryFragment(supportManager)
    }

    fun onClickBackHistory(supportManager: FragmentManager) {
        NavigationManager.gotToCalculatorFragment(supportManager)
    }

}