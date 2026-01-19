package com.project.features.chats.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.core.theme.components.LoadResultView
import com.project.core.theme.previews.PreviewScreenContent
import com.project.features.chats.domain.entities.ChatSession
import com.project.features.chats.presentation.components.ChatItem

@Composable
fun ChatsScreen(
    onClickToChatSession: (Long) -> Unit,
    launchChatScreen: () -> Unit
) {

    val viewModel: ChatViewModel = hiltViewModel()
    val loadResult by viewModel.chatsFlow.collectAsStateWithLifecycle()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = launchChatScreen
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    ) { innerPadding ->
        LoadResultView(
            modifier = Modifier.padding(innerPadding),
            loadResult = loadResult,
            onTryAgain = {},
            content = { chatState ->
                ChatsContent(
                    listChats = chatState.data,
                    onClickToChatSession = onClickToChatSession
                )
            }
        )
    }

}

@Composable
fun ChatsContent(
    listChats: List<ChatSession>,
    onClickToChatSession: (Long) -> Unit
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(listChats) { chat ->
            ChatItem(
                title = chat.title,
                modifier = Modifier.clickable {
                    onClickToChatSession(chat.id)
                }
//                lastMessage = chat.lastMessage
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
                ChatSession(
                    id = 1L,
                    title = "Test title",
//                    lastMessage = "Test last message Test last message Test last message"
                ),
                ChatSession(
                    id = 1L,
                    title = "Test title",
//                    lastMessage = "Test last message Test last message Test last message"
                )
            ),
            onClickToChatSession = {}
        )
    }
}