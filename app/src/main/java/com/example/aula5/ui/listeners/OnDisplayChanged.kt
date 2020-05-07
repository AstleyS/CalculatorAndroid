package com.example.aula5.ui.listeners

import com.example.aula5.data.local.entities.Operation

interface OnDisplayChanged {

    fun onDisplayChanged(value: String?)

    fun onReceiveOperation(listaOperacoes: MutableList<Operation>)
    
}