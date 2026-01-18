package com.project.features.main.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.core.theme.previews.PreviewScreenContent
import com.project.essentials.entities.MessageAuthor
import com.project.features.main.presentation.ChatMessageUiState

@Composable
fun ChatMessageBubble(
    message: ChatMessageUiState
) {
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
            message = ChatMessageUiState(
                id = "1",
                text = "AHahhahaha",
                author = MessageAuthor.USER
            )
        )
    }
}
