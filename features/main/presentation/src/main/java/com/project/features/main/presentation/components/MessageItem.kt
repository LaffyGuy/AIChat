package com.project.features.main.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.core.theme.previews.PreviewScreenContent
import com.project.essentials.longToTime
import com.project.essentials.entities.MessageAuthor
import com.project.features.main.presentation.ChatMessageUiState
import com.project.features.main.presentation.R

@Composable
fun MessageItem(
    message: ChatMessageUiState,
    isUser: Boolean
) {

    Card(
        modifier = Modifier
            .widthIn(max = 280.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            if (isUser) {
                colorResource(R.color.light_purple)
            } else {
                colorResource(R.color.medium_gray).copy(alpha = 0.5f)
            }
        ),
    ) {
        if (message.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                strokeWidth = 2.dp
            )
        } else {
            Column(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 4.dp)
            ) {
                if(!message.isError) {
                    Text(
                        text = message.text,
                        color = Color.Black
                    )
                    Text(
                        text = message.timestamp.longToTime(),
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.End),
                        color = Color.Black
                    )
                } else {
                    Text(text = message.errorText ?: "Error")
                }
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun MessageItemPreview() {
    PreviewScreenContent {
        MessageItem(
            message = ChatMessageUiState(
                id = 1,
                text = "Ahahahah",
                author = MessageAuthor.AI
            ),
            isUser = false
        )
    }
}