package com.project.features.chats.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.core.theme.Dimens
import com.project.core.theme.SmallVerticalSpace
import com.project.core.theme.previews.PreviewScreenContent
import com.project.essentials.formatToDate
import com.project.features.chats.presentation.R
import java.time.LocalDate

//@Composable
//fun ChatItem(
//    title: String,
//    createdAt: LocalDate,
//    onAddToFavorites: () -> Unit,
////    lastMessage: String,
//    modifier: Modifier = Modifier,
//) {
//    Row(
//        modifier = modifier
//            .fillMaxWidth()
//            .padding(Dimens.MediumPadding),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//        Text(
//            text = title,
//            style = MaterialTheme.typography.titleMedium,
//            lineHeight = 24.sp
//        )
//        Row(
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(
//                text = "Created at:\n${createdAt.formatToDate()}",
//                style = MaterialTheme.typography.bodySmall,
//                lineHeight = 20.sp
//            )
//
//            Box {
//                var expanded by remember { mutableStateOf(false) }
//                IconButton(
//                    onClick = {
//                        expanded = true
//                    }
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.MoreVert,
//                        contentDescription = null
//                    )
//                }
//                DropdownMenu(
//                    expanded = expanded,
//                    onDismissRequest = { expanded = false }
//                ) {
//                    DropdownMenuItem(
//                        text = {
//                            Text(text = stringResource(R.string.add_to_favorites))
//                        },
//                        onClick = {
//                            onAddToFavorites()
//                            expanded = false
//                        }
//                    )
//                }
//            }
//
//        }
//
//
////        Text(
////            text = lastMessage,
////            style = MaterialTheme.typography.bodyMedium,
////            lineHeight = 20.sp,
////            modifier = Modifier.padding(start = 4.dp, end = 8.dp, bottom = 8.dp)
////        )
//    }
//}
//
//@Preview(showSystemUi = true)
//@Composable
//private fun ChatItemPreview() {
//    PreviewScreenContent {
//        ChatItem(
//            title = "Test title",
//            createdAt = LocalDate.now(),
//            onAddToFavorites = {}
////            lastMessage = "Test last message Test last message Test last message"
//        )
//    }
//}