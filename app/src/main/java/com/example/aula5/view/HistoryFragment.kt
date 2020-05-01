package com.example.aula5.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Optional
import com.example.aula5.R
import com.example.aula5.viewModel.CalculatorViewModel

class HistoryFragment : Fragment() {

    private lateinit var viewModel: CalculatorViewModel

    @Optional
    @OnClick (R.id.button_back)
    fun onClickBack(view: View) {
      //  viewModel.onClickBackHistory(activity?.supportFragmentManager!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        ButterKnife.bind(this, view)
        return view
    }

}
