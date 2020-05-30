package com.example.aula5.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Optional
import com.example.aula5.R
import com.example.aula5.ui.listeners.OnReceiveLoginAuth
import com.example.aula5.ui.listeners.OnReceiveToken
import com.example.aula5.ui.viewmodels.AuthViewModel

class LoginFragment : Fragment(), OnReceiveLoginAuth, OnReceiveToken {

    private lateinit var viewModel: AuthViewModel

    private lateinit var email: EditText
    private lateinit var password: EditText

    @Optional
    @OnClick (R.id.button_login)
    fun onClickLogin(view: View) {

        if (!email.text.toString().isEmpty() && !password.text.toString().isEmpty()) {
            viewModel.onClickLogin(activity, email.text.toString(), password.text.toString())
        }
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

        email = view.findViewById(R.id.login_email)
        password = view.findViewById(R.id.login_password)

        ButterKnife.bind(this, view)
        return view
    }

    override fun onStart() {
        viewModel.registerListenerAuth(this)
        viewModel.registerListenerToken(this)
        super.onStart()
    }

    override fun onDestroy() {
        viewModel.unregisterListener()
        viewModel.unregisterListenerToken()
        super.onDestroy()
    }

    override fun onReceiveLoginAuth(boolean: Boolean) = boolean.let { viewModel.auth = it }

    override fun onReceiveToken(token: String?) = token.let { viewModel.token = it }


}