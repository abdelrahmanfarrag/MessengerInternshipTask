package com.linkdev.messengerinternshiptask.ui.components.chat

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.linkdev.messengerinternshiptask.data.database.entity.MessengerEntity
import com.linkdev.messengerinternshiptask.ui.utils.toTimeAgo

@Composable
fun ReceiverMessage(message: MessengerEntity = MessengerEntity()) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier
                .wrapContentHeight()
                .padding(8.dp)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.End)
                    .background(
                        color = Color.Blue,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(8.dp), text = message.message.toString(), color = Color.White
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                modifier = Modifier.align(Alignment.End),
                text = message.createdAT.toTimeAgo(),
                color = Color.DarkGray
            )
        }
        Spacer(modifier = Modifier.width(2.dp))
    }
}