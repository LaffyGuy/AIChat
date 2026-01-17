package com.project.features.main.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.core.theme.previews.PreviewScreenContent
import com.project.features.main.domain.entities.ChatMessage
import com.project.features.main.domain.entities.MessageAuthor

@Composable
fun ChatMessageBubble(message: ChatMessage) {
    val isUser = message.author == MessageAuthor.USER

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = if (isUser)
            Arrangement.End
        else
            Arrangement.Start
    ) {
         MessageItem(
             message = message,
             isUser = isUser
         )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ChatMessageBubblePreview() {
    PreviewScreenContent {
        ChatMessageBubble(
            message = ChatMessage(
                id = "1",
                text = "AHahhahaha",
                author = MessageAuthor.USER
            )
        )
    }
}
