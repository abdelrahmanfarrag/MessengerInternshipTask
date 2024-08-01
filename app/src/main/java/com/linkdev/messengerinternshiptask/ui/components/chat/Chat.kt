package com.linkdev.messengerinternshiptask.ui.components.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linkdev.messengerinternshiptask.R
import com.linkdev.messengerinternshiptask.data.database.entity.MessengerEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chat(
    modifier: Modifier = Modifier,
    senderName: String? = null,
    message: String? = null,
    messages: List<MessengerEntity> = arrayListOf(
        MessengerEntity(message = "name1"),
        MessengerEntity(message = "name12")),
    iChatInteraction: IChatInteraction? = null
) {
    val lazyListState = rememberLazyListState()
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = messages) {
        if (messages.isNotEmpty())
            lazyListState.animateScrollToItem(messages.size - 1)
    }
    var isTextFieldHasFocus by remember {
        mutableStateOf(false)
    }
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
                .padding(horizontal = 16.dp, vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.clickable {
                        iChatInteraction?.onBackClick()
                    },
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = Icons.AutoMirrored.Default.ArrowBack.toString(),
                    tint = Color.Blue
                )
                Image(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .size(48.dp),
                    painter = painterResource(id = R.drawable.ic_avatar),
                    contentDescription = ""
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .wrapContentSize()
                ) {
                    Text(text = senderName ?: "NAME", color = Color.White, fontSize = 16.sp)
                    Text(
                        text = "Last seen time",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(id = R.drawable.ic_video),
                    contentDescription = "",
                    tint = Color.Blue
                )
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(id = R.drawable.ic_microphone),
                    contentDescription = "",
                    tint = Color.Blue
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f, true)
                .fillMaxWidth(),
            state = lazyListState
        ) {
            itemsIndexed(messages) { index, message ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = if (isIndexOdd(index)) Arrangement.Start else Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (isIndexOdd(index))
                        ReceiverMessage(message = message)
                    else
                        SenderMessage(messageEntity = message)
                }
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.End)
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(id = R.drawable.ic_camera),
                contentDescription = "",
                tint = Color.Blue
            )
            OutlinedTextField(keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                value = message ?: "",
                modifier = Modifier
                    .imePadding()
                    .onFocusChanged {
                        isTextFieldHasFocus = it.isFocused
                    },
                shape = RoundedCornerShape(16.dp),

                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.DarkGray,
                    unfocusedBorderColor = Color.DarkGray,
                    containerColor = Color.DarkGray
                ),
                onValueChange = {
                    iChatInteraction?.onUpdateTextInMessage(it)
                },
                label = {
                    if (message.isNullOrEmpty() || !isTextFieldHasFocus)
                        Text(
                            text = stringResource(id = R.string.enter_message),
                            color = Color.White,
                            fontSize = 12.sp
                        )
                })
            Icon(
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        keyboardController?.hide()
                        iChatInteraction?.sendMessage()
                    },
                painter = painterResource(id = R.drawable.ic_sende),
                contentDescription = "",
                tint = Color.Blue
            )
        }

    }
}

private fun isIndexOdd(index: Int): Boolean {
    return (index) % 2 == 0
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview_Chat() {
    Chat(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
    )
}