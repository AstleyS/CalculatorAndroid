package com.example.aula5.domain.calculator

import com.example.aula5.data.local.list.ListStorage
import com.example.aula5.data.local.entities.Operation
import com.example.aula5.data.local.room.dao.OperationDao
import com.example.aula5.ui.listeners.OnReceiveOperationsChanged
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder
class CalculatorLogic(private val storage: OperationDao) {


    fun insertSymbol(display: String, symbol: String): String {

        if (display == "0") return symbol
        return "$display$symbol"

    }

    fun insertOperation(display: String, symbol: String): String {

        when (symbol) {

            "del" -> {
                if (display.length == 1) return "0"
                return display.substring(0, display.length - 1)
            }
            "C" -> return "0"
            else -> {
                if (display[display.length -1] in "/*-+") return "${display.substring(0, display.length - 1)}$symbol"
                return "$display$symbol"
            }

        }
    }

    fun performeOperation(expression: String): Double {
        val expressionBuilder = ExpressionBuilder(expression).build()
        val result = expressionBuilder.evaluate()
        CoroutineScope(Dispatchers.IO).launch {
            storage.insert(
                Operation(
                    expression,
                    result
                )
            )

        }
        return expressionBuilder.evaluate()
    }

    fun getAll(listener: OnReceiveOperationsChanged?) {
        CoroutineScope(Dispatchers.IO).launch {
            val operations = storage.getAll()
            listener?.onReceiveOperations(operations)
        }
    }

    fun removeOperation(operation: Operation) {
        CoroutineScope(Dispatchers.IO).launch {
            storage.removeOperation(operation)
        }
    }

}

