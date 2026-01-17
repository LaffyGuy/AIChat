package com.project.features.main.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.project.features.main.domain.entities.ChatMessage
import com.project.features.main.domain.entities.MessageAuthor
import com.project.features.main.presentation.R

@Composable
fun MessageItem(
    message: ChatMessage,
    isUser: Boolean
) {

    Box(
        modifier = Modifier
            .background(
                color = if (isUser)
                    colorResource(R.color.medium_gray).copy(alpha = 0.5f)
                else
                    MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(12.dp)
            .widthIn(max = 280.dp)
    ) {
//        Column(
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.Start
//        ) {
//            if(!isUser && !isGenerated) {
//                CircularProgressIndicator()
//            } else {
//                Text(
//                    text = message.text,
//                    color = if (isUser)
//                        Color.Black
//                    else
//                        MaterialTheme.colorScheme.onSurfaceVariant,
//                    modifier = Modifier.padding(2.dp)
//                )
//                Text(
//                    text = message.timestamp.longToTime(),
//                    fontSize = 12.sp,
//                    color = if (isUser)
//                        Color.Black
//                    else
//                        MaterialTheme.colorScheme.onSurfaceVariant,
//                    modifier = Modifier
//                        .align(Alignment.End)
//                        .padding(2.dp)
//                )
//            }
//        }
//    }

        if (message.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                strokeWidth = 2.dp
            )
        } else {
            Column {
                if(!message.isError) {
                    Text(text = message.text)
                    Text(
                        text = message.timestamp.longToTime(),
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.End)
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
            message = ChatMessage(
                id = "dsds",
                text = "Ahahahah",
                author = MessageAuthor.AI
            ),
            isUser = false
        )
    }
}