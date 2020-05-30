package com.example.aula5.domain.calculator


import android.util.Log
import com.example.aula5.data.remote.requests.Operation
import com.example.aula5.data.local.room.dao.OperationDao
import com.example.aula5.data.remote.services.OperationService
import com.example.aula5.ui.listeners.OnReceiveOperations
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder
import retrofit2.Retrofit

class CalculatorLogic(private val retrofit: Retrofit /*private val storage: OperationDao*/) {

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
/*
        val service = retrofit.create(OperationService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val operacao = Operation(expression, result)
            val response = service.postOperation("",operacao)

            if (response.isSuccessful) {
                response.body()
                Log.i(this::class.java.simpleName, response.message())
            } else {
                Log.i(this::class.java.simpleName, response.errorBody().toString())
            }
        }

 */
        return result
    }

    fun getAll(listener: OnReceiveOperations?, token: String?) {

        val service = retrofit.create(OperationService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getOperation(token)

            if (response.isSuccessful) {
                val operacoes = response.body()
                listener?.onReceiveOperations(operacoes!!)
                Log.i(this::class.java.simpleName, response.message())
            } else {
                Log.i(this::class.java.simpleName, response.message())
            }
        }
    }

    /*
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

    fun getAll(listener: OnReceiveOperations?) {
        CoroutineScope(Dispatchers.IO).launch {
            val operations = storage.getAll()
            listener?.onReceiveOperations(operations) // notifies
        }
    }

    fun removeOperation(operation: Operation) {
        CoroutineScope(Dispatchers.IO).launch {
            storage.removeOperation(operation)
        }
    }
     */
    fun deleteOperations() {

        val service = retrofit.create(OperationService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.deleteOperations("JWT")

            if (response.isSuccessful) {
                response.body()
                Log.i(this::class.java.simpleName, response.message())
            } else {
                Log.i(this::class.java.simpleName, response.errorBody().toString())
            }
        }

    }

}

