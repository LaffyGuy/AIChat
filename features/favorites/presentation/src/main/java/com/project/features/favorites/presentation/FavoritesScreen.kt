package com.project.features.favorites.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
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
import com.project.features.favorites.domain.entities.FavoriteChatSession
import com.project.essentials.entities.ImageSource
import java.time.LocalDate

@Composable
fun FavoritesScreen(
    onClickToChatSession: (Long) -> Unit
) {


    val viewModel: FavoritesViewModel = hiltViewModel()
    val loadResult by viewModel.loadResultFlow.collectAsStateWithLifecycle()

    LoadResultView(
        loadResult = loadResult,
        onTryAgain = {},
        content = { state ->
            if(!state.data.isEmpty()) {
                FavoritesContent(
                    listChats = state.data,
                    onClickToChatSession = onClickToChatSession,
                    onDeleteFromFavorites = { chatId ->
                        viewModel.deleteFromFavorites(chatId, false)
                    }
                )
            } else {
                EmptyFavoriteChatsContent()
            }
        }
    )

}

@Composable
fun FavoritesContent(
    listChats: List<FavoriteChatSession>,
    onDeleteFromFavorites: (Long) -> Unit,
    onClickToChatSession: (Long) -> Unit
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
                    onDeleteFromFavorites(chat.id)
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
fun EmptyFavoriteChatsContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageView(
            imageSource = ImageSource.DrawableRes(R.drawable.ic_no_favorites_chats),
            modifier = Modifier
                .size(Dimens.MediumImageSize)
                .alpha(0.5f)
        )
        MediumVerticalSpace()

        Text(
            text = stringResource(R.string.no_favorites_chats),
            style = MaterialTheme.typography.titleLarge
        )

    }
}

@Preview(showSystemUi = true)
@Composable
private fun EmptyFavoriteChatsContentPreview() {
    PreviewScreenContent {
        EmptyFavoriteChatsContent()
    }
}


@Preview(showSystemUi = true)
@Composable
private fun FavoritesContentPreview() {
    PreviewScreenContent {
        FavoritesContent(
            listChats = listOf(
                FavoriteChatSession(
                    id = 1L,
                    isFavorite = false,
                    createdAt = LocalDate.now(),
                    title = "Test title"),
                FavoriteChatSession(
                    id = 1L,
                    title = "Test title",
                    isFavorite = false,
                    createdAt = LocalDate.now())
            ),
            onClickToChatSession = {},
            onDeleteFromFavorites = {}
        )
    }
}