package com.example.aula5

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
                    instance = ListStorage()
                }
                return instance as ListStorage
            }
        }
    }

    suspend fun insert(operation: Operation) {

        withContext(Dispatchers.IO) {
            Thread.sleep(3000)
            storage.add(operation)

        }
    }



    fun getAll(): List<Operation> = storage.toList()

}