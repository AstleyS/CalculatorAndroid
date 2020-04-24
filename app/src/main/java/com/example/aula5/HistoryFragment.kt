package com.example.aula5

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Optional
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : Fragment() {

    @Optional
    @OnClick (R.id.button_back)
    fun onClickBack(view: View) {
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
