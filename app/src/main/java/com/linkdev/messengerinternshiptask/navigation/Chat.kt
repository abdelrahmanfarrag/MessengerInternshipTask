package com.linkdev.messengerinternshiptask.navigation

import kotlinx.serialization.Serializable

@Serializable
data class Chat(
    val senderName: String? = null,
    val receiverName: String? = null
)
