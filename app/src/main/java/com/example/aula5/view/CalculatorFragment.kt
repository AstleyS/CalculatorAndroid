package com.example.aula5.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Optional
import com.example.aula5.*
import com.example.aula5.viewModel.CalculatorViewModel
import kotlinx.android.synthetic.main.fragment_calculator.*
import kotlinx.android.synthetic.main.fragment_calculator.view.*

const val EXTRA_OPERATIONS = "com.example.aula5.listaOperacoes"

class CalculatorFragment : Fragment(), OnDisplayChanged {

    private var listaOperacoes = mutableListOf(
        Operation("1+1", 2.0)
    )
    private lateinit var viewModel: CalculatorViewModel
    private lateinit var historyAdapter: HistoryAdapter

    /* Funcoes onClick */
    @Optional
    @OnClick (
        R.id.button_00, R.id.button_0, R.id.button_1, R.id.button_2,
        R.id.button_3, R.id.button_4, R.id.button_5, R.id.button_6,
        R.id.button_7, R.id.button_8, R.id.button_9, R.id.button_period,
        R.id.button_adition, R.id.button_divide, R.id.button_sub,
        R.id.button_del, R.id.button_C, R.id.button_product
    )
    fun onClickSymbol(view: View) {

        text_visor.text = viewModel.onClickSymbol(view.tag.toString()).toString()

    }

    @OnClick (R.id.button_equals)
    fun onClickEquals(view: View) {

        text_visor.text = viewModel.onClickEquals().toString()

        /*
        Log.i(TAG, "Click no botão =")
        val expression = ExpressionBuilder(text_visor.text.toString()).build()
        val operacao = text_visor.text
        text_visor.text = expression.evaluate().toString()

        listaOperacoes.add(
            Operation(
                operacao.toString(),
                text_visor.text.toString()
            )
        )
        historyAdapter = HistoryAdapter(
            activity as Context,
            R.layout.item_expression,
            ArrayList(listaOperacoes)
        )
        list_historic?.adapter = historyAdapter
        historic?.text = listaOperacoes.get(listaOperacoes.size - 1).toString()

        Log.i(TAG, "O resultado da expressão é ${text_visor.text}")
         */
    }

    /* Funcionalidade Botão de listar historico */
    @Optional
    @OnClick (R.id.button_list_historic)
    fun onClickHistory(view: View) {
        viewModel.onClickHistory(activity?.supportFragmentManager!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /* Atualizar */
        historyAdapter = HistoryAdapter(
            activity as Context,
            R.layout.item_expression,
            ArrayList(listaOperacoes)
        )
        list_historic?.adapter = historyAdapter
        historic?.text = listaOperacoes.get(listaOperacoes.size - 1).toString()

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)
        viewModel = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onStart() {
        viewModel.registerListener(this)
        super.onStart()
    }

    override fun onDisplayChanged(value: String?) {
        value.let { text_visor.text = it }
    }

    override fun onDestroy() {
        viewModel.unregisterListener()
        super.onDestroy()
    }
}
