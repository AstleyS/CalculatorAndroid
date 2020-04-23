package com.example.aula5


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.text.SimpleDateFormat

const val EXTRA_NAME = "com.example.aula5.listaOperacoes"

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName
    private val VISOR_KEY = "visor"
    private val HISTORIC_KEY = "historic"
    private val LIST_HISTORIC_KEY = "list_historic"

    private var listaOperacoes = arrayListOf("1+1=2")
    private lateinit var historyAdapter: HistoryAdapter

    /* Funcoes onClick */
     fun onClickSymbol(view: View) {

        val symbol = view.tag.toString()
        //val padrao = "hh:mm:ss"
        //val simpleDateFormat = SimpleDateFormat(padrao)

        Log.i(TAG, "Click no botão $symbol")
        if (text_visor.text.equals("0")) {
            text_visor.text = symbol
        } else {
            text_visor.append(symbol)
        }
    }
     fun onClickOperation(view: View) {

        val operation = view.tag.toString()
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

        /* Funcionalidade Botões Operações */
        button_equals.setOnClickListener {
            onClickEquals()
        }

        /* Funcionalidade Botão de listar historico */
        button_list_historic?.setOnClickListener {
            val intent = Intent(this, ListHistoricActivity::class.java)
            intent.apply { putStringArrayListExtra(EXTRA_NAME, listaOperacoes) }
            startActivity(intent)
            finish()
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
        outState.run { putString(VISOR_KEY, text_visor.text.toString())}
        super.onSaveInstanceState(outState)
    }

}