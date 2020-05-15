package com.example.aula5.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Optional
import com.example.aula5.R
import com.example.aula5.ui.viewmodels.AuthViewModel

class LoginFragment : Fragment() {

    private lateinit var viewModel: AuthViewModel

    @Optional
    @OnClick (R.id.button_login)
    fun onClickLogin(view: View) {
        viewModel.onClickLogin(activity)
    }

    @Optional
    @OnClick (R.id.button_register)
    fun onClickRegister(view: View) {
        viewModel.onClickRegister(activity)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

        ButterKnife.bind(this, view)
        return view
    }

}