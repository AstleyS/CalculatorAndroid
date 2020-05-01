package com.example.aula5.domain

import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorLogic {

    fun insertSymbol(display: String, symbol: String): String {
        when (symbol) {
            "0" -> return symbol
            "del" -> {
                if (display.length == 1) return "0"
                return display.substring(0, display.length - 1)
            }
            "C" -> return "0"
            else -> {
                if (symbol.equals("0")) return symbol
                return "$display$symbol"
            }

        }
    }

    fun performeOperation(expression: String): Double {
        val expressionBuilder = ExpressionBuilder(expression).build()
        return expressionBuilder.evaluate()
    }

}