package com.project.features.main.presentation

import com.project.essentials.entities.MessageAuthor
import java.util.UUID

data class ChatMessageUiState(
    val id: String = UUID.randomUUID().toString(),
    val text: String,
    val author: MessageAuthor,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorText: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)
