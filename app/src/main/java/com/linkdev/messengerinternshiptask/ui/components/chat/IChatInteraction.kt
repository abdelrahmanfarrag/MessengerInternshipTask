package com.linkdev.messengerinternshiptask.ui.components.chat

interface IChatInteraction {
    fun onUpdateTextInMessage(text: String?)
    fun sendMessage()
    fun onBackClick()
}