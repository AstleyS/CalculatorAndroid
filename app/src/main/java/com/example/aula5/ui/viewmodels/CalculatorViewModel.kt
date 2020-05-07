package com.example.aula5.ui.viewmodels

import android.app.Application
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.AndroidViewModel
import com.example.aula5.data.local.entities.Operation
import com.example.aula5.data.local.room.CalculatorDatabase
import com.example.aula5.ui.utils.NavigationManager
import com.example.aula5.ui.listeners.OnDisplayChanged
import com.example.aula5.domain.calculator.CalculatorLogic
import com.example.aula5.ui.listeners.OnReceiveOperationsChanged


class CalculatorViewModel(application: Application): AndroidViewModel(application) {

    private val storage = CalculatorDatabase.getInstance(application).operationDao()
    private val calculatorLogic = CalculatorLogic(storage)

    private var listener: OnDisplayChanged? = null
    private var listenerOperation: OnReceiveOperationsChanged? = null
    var display: String = "0"
    var listaOperacoes = mutableListOf<Operation>()

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
        listaOperacoes = calculatorLogic.getAll()
        notifyOnReceiveOperationChanged()
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

    private fun notifyOnReceiveOperationChanged() {
        listenerOperation?.onReceiveOperations(listaOperacoes)
    }

    fun registerListener(listener: OnDisplayChanged) {
        this.listener = listener
        listener.onDisplayChanged(display)
    }

    fun registerListenerOperation(listener: OnReceiveOperationsChanged) {
        this.listenerOperation = listener
        listener.onReceiveOperations(listaOperacoes)
    }

    fun unregisterListener() {
        listener = null
    }

    fun unregisterListenerOperation() {
        listenerOperation = null
    }

}
