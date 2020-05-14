package com.example.aula5.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Optional
import com.example.aula5.data.local.entities.Operation
import com.example.aula5.R
import com.example.aula5.ui.activities.MainActivity
import com.example.aula5.ui.adapters.HistoryAdapter
import com.example.aula5.ui.viewmodels.CalculatorViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : Fragment() {

    private lateinit var viewModel: CalculatorViewModel
    private lateinit var historyAdapter: HistoryAdapter
    var listaOperacoes = mutableListOf<Operation>()

    @Optional
    @OnClick (R.id.button_back)
    fun onClickBack(view: View) {
        viewModel.onClickBackHistory(activity?.supportFragmentManager!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        viewModel = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
        updateList()

        ButterKnife.bind(this, view)
        return view
    }

    fun updateList() {

        viewModel.getOperations()

        // Atualizar
        historyAdapter = HistoryAdapter(
            activity as Context,
            R.layout.item_expression,
            ArrayList(listaOperacoes)
        )

        list_historic?.layoutManager = LinearLayoutManager(activity as Context)
        list_historic?.adapter = historyAdapter
    }

}
