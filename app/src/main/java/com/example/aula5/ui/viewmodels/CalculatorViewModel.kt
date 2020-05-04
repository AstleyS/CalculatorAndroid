package com.example.aula5.ui.viewmodels

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.example.aula5.Operation
import com.example.aula5.ui.utils.NavigationManager
import com.example.aula5.ui.listeners.OnDisplayChanged
import com.example.aula5.domain.calculator.CalculatorLogic


class CalculatorViewModel: ViewModel() {

    private var listener: OnDisplayChanged? = null
    private val calculatorLogic = CalculatorLogic()
    var display: String = "0"

    fun onClickSymbol(symbol: String): String {
        display = calculatorLogic.insertSymbol(display, symbol)
        notifyOnDisplayChanged()
        return display
    }

    fun onClickEquals(): String {
        display = calculatorLogic.performeOperation(display).toString()
        notifyOnDisplayChanged()
        return display
    }

    fun getOperations() : MutableList<Operation> {
        return calculatorLogic.getAll()
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
