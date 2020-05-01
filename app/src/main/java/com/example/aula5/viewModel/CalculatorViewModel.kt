package com.example.aula5.viewModel

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.example.aula5.NavigationManager
import com.example.aula5.OnDisplayChanged
import com.example.aula5.domain.CalculatorLogic


class CalculatorViewModel: ViewModel() {

    private var listener: OnDisplayChanged? = null
    private val calculatorLogic = CalculatorLogic()
    var display: String = "0"


    fun onClickSymbol(symbol:String) {
        display = calculatorLogic.insertSymbol(display, symbol)
        notifyOnDisplayChanged()
    }

    fun onClickEquals() {
        val result = calculatorLogic.performeOperation(display)
        display = result.toString()
        notifyOnDisplayChanged()
    }

    fun onClickHistory(supportManager: FragmentManager) {
        NavigationManager.gotToHistoryFragment(supportManager)
    }

    fun onClickBackHistory(supportManager: FragmentManager) {
        NavigationManager.gotToCalculatorFragment(supportManager)
    }

    private fun notifyOnDisplayChanged() {
        listener?.onDisplayChanged(display)
    }

    fun registerListener(listener: OnDisplayChanged) {
        this.listener = listener
        listener.onDisplayChanged(display)
    }

    fun unregisterListener() {
        listener = null
    }

}
