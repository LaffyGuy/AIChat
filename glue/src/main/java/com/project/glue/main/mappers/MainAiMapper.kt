package com.project.glue.main.mappers

import com.project.data.aichat.entities.ChatMessageDataEntity
import com.project.features.main.domain.entities.ChatMessage

fun ChatMessageDataEntity.toChatMessage(): ChatMessage {
    return ChatMessage(
        id = id,
        text = text,
        author = author,
        timestamp = timestamp
    )
}