package com.example.aula5.ui.viewmodels

import android.app.Application
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.AndroidViewModel
import com.example.aula5.data.local.entities.Operation
import com.example.aula5.data.local.room.CalculatorDatabase
import com.example.aula5.ui.utils.NavigationManager
import com.example.aula5.ui.listeners.OnDisplayChanged
import com.example.aula5.domain.calculator.CalculatorLogic
import com.example.aula5.ui.listeners.OnReceiveOperations


class CalculatorViewModel(application: Application): AndroidViewModel(application) {

    private val storage = CalculatorDatabase.getInstance(application).operationDao()
    private val calculatorLogic = CalculatorLogic(storage)

    private var listener: OnDisplayChanged? = null
    private var listenerOperation: OnReceiveOperations? = null
    var display: String = "0"

    fun onClickSymbol(symbol: String) {
        display = calculatorLogic.insertSymbol(display, symbol)
        notifyOnDisplayChanged()
    }

    fun onClickOperation(symbol: String) {
        display = calculatorLogic.insertOperation(display, symbol)
        notifyOnDisplayChanged()
    }

    fun onClickEquals() {

        if (display[display.length -1] !in "/*-+") {
            display = calculatorLogic.performeOperation(display).toString()
            notifyOnDisplayChanged()
        }
    }

    fun getOperations()  {
        calculatorLogic.getAll(listenerOperation)
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

    fun registerListenerOperation(listener: OnReceiveOperations) {
        this.listenerOperation = listener
    }

    fun unregisterListener() {
        listener = null
    }

    fun unregisterListenerOperation() {
        listenerOperation = null
    }

}
