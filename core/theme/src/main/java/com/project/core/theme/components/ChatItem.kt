package com.project.core.theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.core.theme.Dimens
import com.project.core.theme.R
import com.project.core.theme.previews.PreviewScreenContent
import com.project.essentials.formatToDate
import java.time.LocalDate

@Composable
fun ChatItem(
    title: String,
    createdAt: LocalDate,
    isFavorite: Boolean,
    onAddDeleteFavorites: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(Dimens.SmallPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                lineHeight = 24.sp
            )

            Text(
                text = createdAt.formatToDate(),
                style = MaterialTheme.typography.bodySmall,
                lineHeight = 20.sp
            )
        }
        Box(
            contentAlignment = Alignment.Center
        ) {
            var expanded by remember { mutableStateOf(false) }
            IconButton(
                onClick = {
                    expanded = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                containerColor = Color.White
            ) {
                DropdownMenuItem(
                    text = {
                        if (!isFavorite) {
                            Text(
                                text = stringResource(R.string.add_to_favorites),
                                color = Color.Black
                            )
                        } else {
                            Text(
                                text = stringResource(R.string.delete_from_favorites),
                                color = Color.Black
                            )
                        }

                    },
                    onClick = {
                        onAddDeleteFavorites()
                        expanded = false
                    }
                )
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun ChatItemPreview() {
    PreviewScreenContent {
        ChatItem(
            title = "Test title",
            createdAt = LocalDate.now(),
            isFavorite = true,
            onAddDeleteFavorites = {}
        )
    }
}