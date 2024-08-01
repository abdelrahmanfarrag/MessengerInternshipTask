package com.linkdev.messengerinternshiptask.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.linkdev.messengerinternshiptask.data.database.dao.MessengerDao
import com.linkdev.messengerinternshiptask.data.database.entity.MessengerEntity

@Database(entities = [MessengerEntity::class], version = 3)
abstract class MessengerDatabase : RoomDatabase() {

    abstract fun messengerDao(): MessengerDao

    companion object {
        private const val MESSENGER_DATABASE = "messenger.db"
        @Volatile
        private var INSTANCE: MessengerDatabase? = null

        fun getInstance(context: Context): MessengerDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        MessengerDatabase::class.java,
                        MESSENGER_DATABASE
                    ).build()
                }
                return instance
            }
        }
    }
}