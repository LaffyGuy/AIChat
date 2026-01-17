package com.project.features.chats.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.core.theme.components.LoadResultView
import com.project.core.theme.previews.PreviewScreenContent
import com.project.features.chats.domain.entities.Chat
import com.project.features.chats.presentation.components.ChatItem

@Composable
fun ChatsScreen() {

    val viewModel: ChatViewModel = hiltViewModel()
    val loadResult by viewModel.chatsFlow.collectAsStateWithLifecycle()

    LoadResultView(
        loadResult = loadResult,
        onTryAgain = {},
        content = { chatState ->
            ChatsContent(
                listChats = chatState.data
            )
        }
    )

}

@Composable
fun ChatsContent(
    listChats: List<Chat>
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(listChats) { chat ->
            ChatItem(
                title = chat.title,
                lastMessage = chat.lastMessage
            )
            HorizontalDivider()
        }

    }

}

@Preview
@Composable
private fun ChatsContentPreview() {
    PreviewScreenContent {
        ChatsContent(
            listChats = listOf(
                Chat(
                    id = 1L,
                    title = "Test title",
                    lastMessage = "Test last message Test last message Test last message"
                ),
                Chat(
                    id = 1L,
                    title = "Test title",
                    lastMessage = "Test last message Test last message Test last message"
                )
            )
        )
    }
}