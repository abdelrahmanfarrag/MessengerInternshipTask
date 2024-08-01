package com.linkdev.messengerinternshiptask.ui.components.landing

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MessengerLandingViewModel : ViewModel() {

    private val _messengerState = MutableStateFlow(MessengerLandingState())
    val messengerState = _messengerState.asStateFlow()
    val currentState: MessengerLandingState
        get() = messengerState.value

    fun updateReceiverName(receiverName: String?) {
        _messengerState.update {
            it.copy(
                receiverName = receiverName,
                isContinueEnabled = !receiverName.isNullOrEmpty()
                        && receiverName.isNotBlank()
                        && !currentState.senderName.isNullOrEmpty()
                        && currentState.senderName?.isNotBlank() == true
            )
        }
    }

    fun updateSenderName(senderName: String?) {
        _messengerState.value = _messengerState.value.copy(
            senderName = senderName,
            isContinueEnabled = !senderName.isNullOrEmpty()
                    && senderName.isNotBlank()
                    && !currentState.receiverName.isNullOrEmpty()
                    && currentState.receiverName?.isNotBlank() == true

        )
    }


}