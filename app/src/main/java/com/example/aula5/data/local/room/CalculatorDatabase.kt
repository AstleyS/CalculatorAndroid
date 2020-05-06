package com.example.aula5.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.aula5.data.local.entities.Operation
import com.example.aula5.data.local.room.dao.OperationDao

@Database(entities = arrayOf(Operation::class), version = 1)
abstract class CalculatorDatabase : RoomDatabase() {

    abstract fun operationDao(): OperationDao

    companion object {

        private var instance: CalculatorDatabase? = null

        fun getInstance(applicationContext: Context): CalculatorDatabase {
            synchronized(this) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        applicationContext,
                        CalculatorDatabase::class.java,
                        "calculator_db"
                    ).build()
                }
                return instance as CalculatorDatabase
            }
        }
    }

}