package com.example.aula5.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.aula5.data.local.entities.Operation
import com.example.aula5.data.local.entities.User
import com.example.aula5.data.local.room.dao.OperationDao
import com.example.aula5.data.local.room.dao.UserDao

@Database(entities = arrayOf(User::class), version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        private var instance: UserDatabase? = null

        fun getInstance(applicationContext: Context): UserDatabase {
            synchronized(this) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        applicationContext,
                        UserDatabase::class.java,
                        "user_db"
                    ).build()
                }
                return instance as UserDatabase
            }
        }
    }

}