package com.project.features.chats.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.core.theme.Dimens
import com.project.core.theme.MediumVerticalSpace
import com.project.core.theme.components.ChatItem
import com.project.core.theme.components.ImageView
import com.project.core.theme.components.LoadResultView
import com.project.core.theme.previews.PreviewScreenContent
import com.project.essentials.entities.ImageSource
import com.project.features.chats.domain.entities.ChatSession
import java.time.LocalDate

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
                if (!chatState.data.isEmpty()) {
                    ChatsContent(
                        listChats = chatState.data,
                        onClickToChatSession = onClickToChatSession,
                        onAddToFavorites = { chatId ->
                            viewModel.updateFavoriteStatus(chatId, true)
                        }
                    )
                } else {
                    EmptyChatsContent()
                }
            }
        )
    }

}

@Composable
fun ChatsContent(
    listChats: List<ChatSession>,
    onClickToChatSession: (Long) -> Unit,
    onAddToFavorites: (Long) -> Unit
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(listChats) { chat ->
            ChatItem(
                title = chat.title,
                createdAt = chat.createdAt,
                isFavorite = chat.isFavorite,
                onAddDeleteFavorites = {
                    onAddToFavorites(chat.id)
                },
                modifier = Modifier.clickable {
                    onClickToChatSession(chat.id)
                }
            )
            HorizontalDivider()
        }

    }

}

@Composable
fun EmptyChatsContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageView(
            imageSource = ImageSource.DrawableRes(R.drawable.ic_no_chats),
            modifier = Modifier
                .size(Dimens.MediumImageSize)
                .alpha(0.5f)
        )
        MediumVerticalSpace()

        Text(
            text = stringResource(R.string.no_chats),
            style = MaterialTheme.typography.titleLarge
        )

    }
}

@Preview(showSystemUi = true)
@Composable
private fun EmptyChatsContentPreview() {
    PreviewScreenContent {
        EmptyChatsContent()
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
                    isFavorite = false,
                    createdAt = LocalDate.now()
                ),
                ChatSession(
                    id = 1L,
                    title = "Test title",
                    isFavorite = true,
                    createdAt = LocalDate.now()
                )
            ),
            onClickToChatSession = {},
            onAddToFavorites = {}
        )
    }
}