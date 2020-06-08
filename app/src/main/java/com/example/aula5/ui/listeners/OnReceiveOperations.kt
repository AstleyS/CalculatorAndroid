package com.example.aula5.ui.listeners

import com.example.aula5.data.local.entities.Operation


interface OnReceiveOperations {

    fun onReceiveOperations(listaOperacoes: MutableList<Operation>)

}