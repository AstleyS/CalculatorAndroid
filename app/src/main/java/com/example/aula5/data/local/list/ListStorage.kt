package com.example.aula5.data.local.list

import com.example.aula5.Operation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListStorage {

    private val storage = mutableListOf<Operation>()

    companion object {

        private var instance: ListStorage? = null

        fun getInstance(): ListStorage {
            synchronized(this) {
                if (instance == null) {
                    instance =
                        ListStorage()
                }
                return instance as ListStorage
            }
        }
    }

    fun insert(operation: Operation) {

        CoroutineScope(Dispatchers.IO).launch {
            storage.add(operation)

        }
    }

    fun getAll(): MutableList<Operation> = storage
}