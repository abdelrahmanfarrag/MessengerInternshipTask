package com.linkdev.messengerinternshiptask.ui.components.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.linkdev.messengerinternshiptask.R

@Composable
fun MessengerLanding(
    modifier: Modifier = Modifier,
    senderName: String? = "",
    receiverName: String? = "",
    isEnabled: Boolean = false,
    iMessengerLandingActions: IMessengerLandingActions? = null
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        val brush = Brush.horizontalGradient(listOf(Color.Red, Color.Blue))
        val disabledBrush =
            Brush.horizontalGradient(listOf(Color.Black, Color.DarkGray, Color.Gray))

        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(56.dp),
                painter = painterResource(id = R.drawable.ic_messenger),
                contentDescription = R.drawable.ic_messenger.toString()
            )
            Text(
                text = stringResource(id = R.string.welcome_to_messenger),
                color = Color.Black
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                value = senderName ?: "", onValueChange = {
                    iMessengerLandingActions?.onUpdateSenderName(it)
                }, shape = RoundedCornerShape(8.dp), label = {
                    Text(text = stringResource(id = R.string.sender_name))
                })

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                value = receiverName ?: "",
                onValueChange = {
                    iMessengerLandingActions?.onUpdateReceiverName(it)
                },
                shape = RoundedCornerShape(8.dp),
                label = {
                    Text(text = stringResource(id = R.string.receiver_name))
                })
            Button(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .background(
                        brush = if (isEnabled) brush else disabledBrush,
                        shape = RoundedCornerShape(16.dp)
                    ),
                onClick = { iMessengerLandingActions?.onContinueClick() },
                enabled = isEnabled,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Transparent,
                    disabledContentColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent
                )
            )
            {
                Text(text = stringResource(id = R.string.continue_txt), color = Color.White)
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview_MessengerLanding() {
    MessengerLanding(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp), senderName = "Abdelrahman",
        receiverName = "Ahmed",
        isEnabled = true
    )
}