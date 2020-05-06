package com.example.aula5.data.local.list

import com.example.aula5.data.local.entities.Operation

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
        storage.add(operation)
    }

    fun getAll(): MutableList<Operation> = storage

    fun removeOperation(operation: Operation) = storage.remove(operation)
}