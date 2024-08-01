package com.linkdev.messengerinternshiptask.ui.components.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linkdev.messengerinternshiptask.data.database.dao.MessengerDao
import com.linkdev.messengerinternshiptask.data.database.entity.MessengerEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar


class ChatViewModel(
    private val mMessengerDao: MessengerDao,
    private val senderName: String?,
    private val receiverName: String?
) : ViewModel() {

    private val _messengerState = MutableStateFlow(MessengerState())
    val messageState = _messengerState.asStateFlow()

    private val currentState: MessengerState
        get() = _messengerState.value

    init {
        updateMessageList()
        _messengerState.update {
            it.copy(
                senderName = senderName,
                receiverName = receiverName
            )
        }
    }

    fun onSendMessageClick() {
        viewModelScope.launch(Dispatchers.IO) {
            if (!currentState.currentMessage.isNullOrEmpty() || currentState.currentMessage?.isNotEmpty() == true) {
                mMessengerDao.sendNewMessage(
                    MessengerEntity(
                        message = currentState.currentMessage,
                        senderName = currentState.senderName,
                        receiverName = currentState.receiverName,
                        createdAT = currentDate()
                    )
                )
                updateMessageList()
            }
        }
    }

    private fun updateMessageList() {
        viewModelScope.launch(Dispatchers.IO) {
            mMessengerDao.getAllSavedTextMessages().collect { messages ->
                _messengerState.update {
                    it.copy(
                        currentMessage = "",
                        messages = messages
                    )
                }
            }
        }
    }

    fun updateMessage(message: String?) {
        _messengerState.update {
            it.copy(
                currentMessage = message
            )
        }
    }

    private fun currentDate(): Long {
        val calendar = Calendar.getInstance()
        return calendar.timeInMillis
    }
}