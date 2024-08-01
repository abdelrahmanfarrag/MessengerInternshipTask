package com.linkdev.messengerinternshiptask.ui.components.chat

import com.linkdev.messengerinternshiptask.data.database.entity.MessengerEntity

data class MessengerState(
    val senderName: String? = null,
    val receiverName: String? = null,
    val messages: List<MessengerEntity>? = null,
    val currentMessage: String? = null
)
