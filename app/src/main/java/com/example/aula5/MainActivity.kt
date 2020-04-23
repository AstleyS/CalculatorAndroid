package com.example.aula5

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_expression.view.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName
    private val VISOR_KEY = "visor"
    private var listaOperacoes = arrayListOf("1+1=2")
    private lateinit var historyAdapter: HistoryAdapter

    /* Funcoes onClick */
    private fun onClickSymbol(symbol: String) {

        //val padrao = "hh:mm:ss"
        //val simpleDateFormat = SimpleDateFormat(padrao)

        Log.i(TAG, "Click no botão $symbol")
        if (text_visor.text.equals("0")) {
            text_visor.text = symbol
        } else {
            text_visor.append(symbol)
        }
    }
    private fun onClickOperation(operation: String) {

        val padrao = "hh:mm:ss"
        val simpleDateFormat = SimpleDateFormat(padrao)

        if (operation == "del") {
            Log.i(TAG, "Click no botão DEL")
            if (text_visor.text.length == 1) {
                text_visor.text = "0"
            } else {
                text_visor.text = text_visor.text.substring(0, text_visor.text.length - 1)
            }
        } else if (operation == "C") {
            Log.i(TAG, "Click no botão C")
            text_visor.text = "0"
        } else {
            Log.i(TAG, "Click no botão $operation")
            text_visor.append(operation)
        }

        //Toast.makeText(this, "Metodo: button_$operation\nHora: ${simpleDateFormat.format(Date())}", Toast.LENGTH_SHORT).show()
    }
    private fun onClickEquals() {
        Log.i(TAG, "Click no botão =")
        val expression = ExpressionBuilder(text_visor.text.toString()).build()
        val operacao = text_visor.text
        text_visor.text = expression.evaluate().toString()

        listaOperacoes.add("$operacao = ${text_visor.text}")
        historyAdapter = HistoryAdapter(this, R.layout.item_expression, listaOperacoes)
        list_historic?.adapter = historyAdapter
        historic?.text = listaOperacoes.get(listaOperacoes.size - 1)

        Log.i(TAG, "O resultado da expressão é ${text_visor.text}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "o método onCreate foi invocado")
        setContentView(R.layout.activity_main)
        historyAdapter = HistoryAdapter(this, R.layout.item_expression, listaOperacoes)
        list_historic?.adapter = historyAdapter
        historic?.text = listaOperacoes.get(listaOperacoes.size - 1)

        /* Funcionalidade Botões Numericos */
        button_00?.setOnClickListener {
            onClickSymbol("00")
        }
        button_0.setOnClickListener {
            onClickSymbol("0")
        }
        button_1.setOnClickListener {
            onClickSymbol("1")
        }
        button_2.setOnClickListener {
            onClickSymbol("2")
        }
        button_3.setOnClickListener {
            onClickSymbol("3")
        }
        button_4.setOnClickListener {
            onClickSymbol("4")
        }
        button_5.setOnClickListener {
            onClickSymbol("5")
        }
        button_6.setOnClickListener {
            onClickSymbol("6")
        }
        button_7.setOnClickListener {
            onClickSymbol("7")
        }
        button_8.setOnClickListener {
            onClickSymbol("8")
        }
        button_9.setOnClickListener {
            onClickSymbol("9")
        }
        button_period.setOnClickListener {
            onClickSymbol(".")
        }

        /* Funcionalidade Botões Operações */
        button_adition.setOnClickListener {
            onClickOperation("+")
        }
        button_sub.setOnClickListener {
            onClickOperation("-")
        }
        button_del.setOnClickListener {
            onClickOperation("del")
        }
        button_C.setOnClickListener {
            onClickOperation("C")
        }
        button_divide.setOnClickListener {
            onClickOperation("/")
        }
        button_equals.setOnClickListener {
            onClickEquals()
        }
    }

    override fun onDestroy() {
        Log.i(TAG, "O método onDestroy foi invocado")
        super.onDestroy()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        text_visor.text = savedInstanceState.getString(VISOR_KEY)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run { putString(VISOR_KEY, text_visor.text.toString()) }
        super.onSaveInstanceState(outState)
    }
}