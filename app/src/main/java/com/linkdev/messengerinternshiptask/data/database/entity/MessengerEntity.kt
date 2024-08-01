package com.linkdev.messengerinternshiptask.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messenger")
data class MessengerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val senderName: String? = null,
    val receiverName: String? = null,
    val createdAT: Long? = null,
    val message: String? = null,
    val isMyMessage: Boolean? = null
)