package com.linkdev.messengerinternshiptask.ui.components.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.linkdev.messengerinternshiptask.data.database.dao.MessengerDao

@Suppress("UNCHECKED_CAST")
class ChatViewModelFactory( private val mMessengerDao: MessengerDao,
    private val mSenderName:String?,
    private val mReceiverName:String?) :ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChatViewModel(mMessengerDao,mSenderName,mReceiverName) as T
    }
}