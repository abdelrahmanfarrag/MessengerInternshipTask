package com.linkdev.messengerinternshiptask

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgs
import androidx.navigation.toRoute
import com.linkdev.messengerinternshiptask.data.database.MessengerDatabase
import com.linkdev.messengerinternshiptask.ui.components.landing.IMessengerLandingActions
import com.linkdev.messengerinternshiptask.ui.components.landing.MessengerLanding
import com.linkdev.messengerinternshiptask.ui.components.landing.MessengerLandingViewModel
import com.linkdev.messengerinternshiptask.navigation.Chat
import com.linkdev.messengerinternshiptask.navigation.MessengerLanding
import com.linkdev.messengerinternshiptask.ui.components.chat.Chat
import com.linkdev.messengerinternshiptask.ui.components.chat.ChatViewModel
import com.linkdev.messengerinternshiptask.ui.components.chat.ChatViewModelFactory
import com.linkdev.messengerinternshiptask.ui.components.chat.IChatInteraction
import com.linkdev.messengerinternshiptask.ui.theme.MessengerInternshipTaskTheme

class MainActivity : ComponentActivity() {

    private lateinit var mMessengerDatabase: MessengerDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMessengerDatabase = MessengerDatabase.getInstance(this)
        setContent {
            MessengerInternshipTaskTheme {
                val navHostController = rememberNavController()
                NavHost(navController = navHostController, startDestination = MessengerLanding) {
                    composable<MessengerLanding> {
                        val messengerLandingViewModel by viewModels<MessengerLandingViewModel>()
                        val state =
                            messengerLandingViewModel.messengerState.collectAsStateWithLifecycle().value
                        MessengerLanding(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 32.dp),
                            senderName = state.senderName,
                            receiverName = state.receiverName, isEnabled = state.isContinueEnabled,
                            iMessengerLandingActions = object : IMessengerLandingActions {
                                override fun onUpdateSenderName(senderName: String?) {
                                    messengerLandingViewModel.updateSenderName(senderName)
                                }

                                override fun onUpdateReceiverName(receiverName: String?) {
                                    messengerLandingViewModel.updateReceiverName(receiverName)
                                }

                                override fun onContinueClick() {
                                    navHostController.navigate(
                                        Chat(
                                            state.senderName,
                                            state.receiverName
                                        )
                                    )
                                }

                            }
                        )
                    }
                    composable<Chat> {
                        val chatArguments = it.toRoute<Chat>()
                        val chatViewModel by viewModels<ChatViewModel> {
                            ChatViewModelFactory(
                                mMessengerDatabase.messengerDao(),
                                chatArguments.senderName,
                                chatArguments.receiverName
                            )
                        }
                        val state = chatViewModel.messageState.collectAsStateWithLifecycle().value
                        Chat(
                            modifier = Modifier
                                .background(color = Color.Black)
                                .fillMaxSize(),
                            messages = state.messages?: arrayListOf(),
                            senderName = state.senderName,
                            message = state.currentMessage,
                            iChatInteraction = object : IChatInteraction {
                                override fun onUpdateTextInMessage(text: String?) {
                                    chatViewModel.updateMessage(text)
                                }

                                override fun sendMessage() {
                                    chatViewModel.onSendMessageClick()
                                }

                                override fun onBackClick() {
                                    navHostController.popBackStack()
                                }

                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MessengerInternshipTaskTheme {
        Greeting("Android")
    }
}