package com.example.aula5.data.local.list

import com.example.aula5.Operation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ListStorage {

    private val storage = mutableListOf<Operation>(
        Operation("1+1", 2.0)
    )

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

    suspend fun insert(operation: Operation) {

        withContext(Dispatchers.IO) {
            Thread.sleep(3000)
            storage.add(operation)

        }
    }


    fun getAll(): MutableList<Operation> = storage

}