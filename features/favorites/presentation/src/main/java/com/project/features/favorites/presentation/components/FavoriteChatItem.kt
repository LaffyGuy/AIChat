package com.project.features.favorites.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.project.core.theme.Dimens
import com.project.core.theme.previews.PreviewScreenContent
import com.project.essentials.formatToDate
import java.time.LocalDate

@Composable
fun FavoriteChatItem(
    title: String,
    createdAt: LocalDate,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(Dimens.MediumPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            lineHeight = 24.sp
        )

        Text(
            text = "Created at:\n${createdAt.formatToDate()}",
            style = MaterialTheme.typography.bodySmall,
            lineHeight = 20.sp
        )

    }
}

@Preview(showSystemUi = true)
@Composable
private fun FavoriteChatItemPreview() {
    PreviewScreenContent {
        FavoriteChatItem(
            title = "Test title",
            createdAt = LocalDate.now()
        )
    }
}