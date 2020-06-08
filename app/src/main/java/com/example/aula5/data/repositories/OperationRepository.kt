package com.example.aula5.data.repositories

import android.util.Log
import com.example.aula5.data.local.entities.Operation
import com.example.aula5.data.local.room.dao.OperationDao
import com.example.aula5.data.remote.services.OperationService
import com.example.aula5.ui.listeners.OnReceiveOperations
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class OperationRepository(private val local: OperationDao, private val remote: Retrofit) {

    fun getAll(listener: OnReceiveOperations?, token: String?) {

        var operations: MutableList<Operation>? = null
        val service = remote.create(OperationService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getOperation(token)
            print(token)

            if (response.isSuccessful) {
                response.body().let {
                    it?.forEach {
                            op -> operations?.add(op)
                    }
                }
                Log.i(this::class.java.simpleName, response.message())
            } else {
                operations = local.getAll()
                Log.i(this::class.java.simpleName, response.message())
            }

            withContext(Dispatchers.Main) {
                listener?.onReceiveOperations(operations!!)
            }
        }
    }

}