package com.project.features.main.presentation.mappers

import com.project.features.main.domain.entities.ChatMessage
import com.project.features.main.presentation.ChatMessageUiState

fun ChatMessageUiState.toDomain(): ChatMessage =
    ChatMessage(
        id = id,
        text = text,
        author = author,
        timestamp = timestamp
    )