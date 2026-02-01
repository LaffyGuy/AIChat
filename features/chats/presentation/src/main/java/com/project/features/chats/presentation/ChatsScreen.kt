package com.project.features.chats.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
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
import com.project.core.theme.components.SearchView
import java.time.LocalDate

@Composable
fun ChatsScreen(
    onClickToChatSession: (Long) -> Unit
) {

    val viewModel: ChatViewModel = hiltViewModel()
    val chatUiState by viewModel.chatUiState.collectAsStateWithLifecycle()
    val searchField by viewModel.searchFieldValue.collectAsStateWithLifecycle()



    LoadResultView(
        modifier = Modifier.fillMaxSize(),
        loadResult = chatUiState.loadResult,
        onTryAgain = {},
        content = { chatState ->
            if (!chatState.isEmpty()) {
                ChatsContent(
                    listChats = chatState,
                    query = searchField,
                    onQueryChange = viewModel::updateSearchValue,
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

@Composable
fun ChatsContent(
    listChats: List<ChatSession>,
    query: String,
    onQueryChange: (String) -> Unit,
    onClickToChatSession: (Long) -> Unit,
    onAddToFavorites: (Long) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SearchView(
            query = query,
            onQueryChange = onQueryChange
        )
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
            }
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
            imageSource = ImageSource.Empty,
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
            query = "",
            onQueryChange = {},
            onClickToChatSession = {},
            onAddToFavorites = {}
        )
    }
}