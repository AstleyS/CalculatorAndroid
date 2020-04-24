package com.example.aula5

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Optional
import kotlinx.android.synthetic.main.fragment_calculator.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.text.SimpleDateFormat

const val EXTRA_OPERATIONS = "com.example.aula5.listaOperacoes"

class CalculatorFragment : Fragment() {

    private val TAG = MainActivity::class.java.simpleName
    private var listaOperacoes = mutableListOf(Operation("1+1","2"))
    private lateinit var historyAdapter: HistoryAdapter

    /* Funcoes onClick */
    @Optional
    @OnClick (R.id.button_00, R.id.button_0, R.id.button_1,
        R.id.button_2, R.id.button_3, R.id.button_4, R.id.button_5, R.id.button_6,
        R.id.button_7, R.id.button_8, R.id.button_9, R.id.button_period
    )
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

    @OnClick (R.id.button_adition, R.id.button_divide, R.id.button_sub,
        R.id.button_del, R.id.button_C, R.id.button_product)
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

    @OnClick (R.id.button_equals)
    fun onClickEquals(view: View) {

        Log.i(TAG, "Click no botão =")
        val expression = ExpressionBuilder(text_visor.text.toString()).build()
        val operacao = text_visor.text
        text_visor.text = expression.evaluate().toString()

        listaOperacoes.add(Operation(operacao.toString(), text_visor.text.toString()))
        historyAdapter = HistoryAdapter(activity as Context, R.layout.item_expression, ArrayList(listaOperacoes))
        list_historic?.adapter = historyAdapter
        historic?.text = listaOperacoes.get(listaOperacoes.size - 1).toString()

        Log.i(TAG, "O resultado da expressão é ${text_visor.text}")
    }

    /* Funcionalidade Botão de listar historico */
    fun onClickHistory(view: View) {
        /*
        val intent = Intent(this, HistoryActivity::class.java)
        intent.apply { putParcelableArrayListExtra(EXTRA_OPERATIONS, ArrayList(listaOperacoes)) }
        startActivity(intent)
        finish()
        */

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /* Atualizar
        historyAdapter = HistoryAdapter(activity as Context, R.layout.item_expression, ArrayList(listaOperacoes))
        list_historic?.adapter = historyAdapter
        historic?.text = listaOperacoes.get(listaOperacoes.size - 1).toString()
        */

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)
        ButterKnife.bind(this, view)
        return view
    }

}
