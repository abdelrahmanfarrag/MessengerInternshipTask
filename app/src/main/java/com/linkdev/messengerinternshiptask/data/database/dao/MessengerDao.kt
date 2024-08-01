package com.linkdev.messengerinternshiptask.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.linkdev.messengerinternshiptask.data.database.entity.MessengerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MessengerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun sendNewMessage(messengerEntity: MessengerEntity)

    @Query("SELECT * from messenger")
    fun getAllSavedTextMessages(): Flow<List<MessengerEntity>>

}