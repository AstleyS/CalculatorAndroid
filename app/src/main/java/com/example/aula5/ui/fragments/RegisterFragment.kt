package com.example.aula5.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Optional
import com.example.aula5.R
import com.example.aula5.ui.viewmodels.AuthViewModel

class RegisterFragment : Fragment() {

    private lateinit var viewModel: AuthViewModel

    lateinit var name: EditText
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var repeat_password: EditText

    @Optional
    @OnClick (R.id.button_submit_register)
    fun onClickSubmitRegister(view: View) {

        if (!name.text.toString().isEmpty() && !email.text.toString().isEmpty() && !password.text.toString().isEmpty()) {

            if (password.text.toString() == repeat_password.text.toString()) {
                viewModel.onClickSubmitRegister(activity, email.text.toString(), password.text.toString())
            }
        }
    }

    @Optional
    @OnClick (R.id.button_cancel_register)
    fun onClickCancelRegister(view: View) {
        viewModel.onClickCancelRegister(activity)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

        name = view.findViewById(R.id.user_name)
        email = view.findViewById(R.id.user_email)
        password = view.findViewById(R.id.user_pass)
        repeat_password = view.findViewById(R.id.user_repeat_pass)

        ButterKnife.bind(this, view)
        return view
    }


}
