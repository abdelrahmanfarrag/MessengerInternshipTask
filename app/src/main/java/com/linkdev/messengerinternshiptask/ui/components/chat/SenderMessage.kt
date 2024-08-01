package com.linkdev.messengerinternshiptask.ui.components.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.linkdev.messengerinternshiptask.R
import com.linkdev.messengerinternshiptask.data.database.entity.MessengerEntity
import com.linkdev.messengerinternshiptask.ui.utils.toTimeAgo

@Composable
fun SenderMessage(messageEntity: MessengerEntity = MessengerEntity()) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(36.dp),
            painter = painterResource(id = R.drawable.ic_avatar),
            contentDescription = R.drawable.ic_avatar.toString()
        )
        Spacer(modifier = Modifier.width(2.dp))
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .padding(8.dp)
        ) {
            Text(
                modifier = Modifier
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(8.dp), text = messageEntity.message.toString(), color = Color.Black
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = messageEntity.createdAT.toTimeAgo(), color = Color.DarkGray)
        }
    }

}