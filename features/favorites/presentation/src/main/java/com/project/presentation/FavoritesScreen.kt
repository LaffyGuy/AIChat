package com.project.presentation

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.project.core.theme.Dimens
import com.project.core.theme.MediumVerticalSpace
import com.project.core.theme.components.ImageView
import com.project.core.theme.previews.PreviewScreenContent
import com.project.domain.entities.FavoriteChatSession
import com.project.essentials.entities.ImageSource
import com.project.presentation.components.FavoriteChatItem
import java.time.LocalDate

@Composable
fun FavoritesScreen() {

}

@Composable
fun FavoritesContent(
    listChats: List<FavoriteChatSession>,
    onClickToChatSession: (Long) -> Unit
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(listChats) { chat ->
            FavoriteChatItem(
                title = chat.title,
                createdAt = chat.createdAt,
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
            imageSource = ImageSource.DrawableRes(R),
            modifier = Modifier
                .size(Dimens.MediumImageSize)
                .alpha(0.5f)
        )
        MediumVerticalSpace()

        Text(
            text = stringResource(),
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
                    title = "Test title",
//                    lastMessage = "Test last message Test last message Test last message"
                ),
                FavoriteChatSession(
                    id = 1L,
                    title = "Test title",
                    isFavorite = false,
                    createdAt = LocalDate.now()
//                    lastMessage = "Test last message Test last message Test last message"
                )
            ),
            onClickToChatSession = {}
        )
    }
}