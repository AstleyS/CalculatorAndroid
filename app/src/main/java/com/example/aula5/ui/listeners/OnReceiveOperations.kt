package com.example.aula5.ui.listeners

import com.example.aula5.data.remote.requests.Operation

interface OnReceiveOperations {

    fun onReceiveOperations(listaOperacoes: MutableList<Operation>)

}