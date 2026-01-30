package com.project.features.main.presentation

import com.project.essentials.entities.MessageAuthor
import com.project.features.main.domain.entities.ChatMessage

data class ChatMessageUiState(
    val id: Long,
    val text: String,
    val author: MessageAuthor,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorText: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)

fun ChatMessage.toUiState(): ChatMessageUiState {
    return ChatMessageUiState(
        id = id,
        text = text,
        author = author,
        timestamp = timestamp
    )
}
