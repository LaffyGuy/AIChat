package com.project.data.aichat.entities

import com.project.core.data.database.model.ChatMessageEntity
import com.project.essentials.entities.MessageAuthor
import kotlin.Long

data class ChatMessageDataEntity(
    val id: Long = 0,
    val text: String,
    val chatId: Long,
    val author: MessageAuthor,
    val timestamp: Long
)

fun ChatMessageEntity.toChatMessageDataEntity(): ChatMessageDataEntity {
    return ChatMessageDataEntity(
        id = id,
        text = text,
        chatId = chatId,
        author = author,
        timestamp = timestamp
    )
}

fun ChatMessageDataEntity.toChatMessageEntity(): ChatMessageEntity {
    return ChatMessageEntity(
        id = id,
        text = text,
        chatId = chatId,
        author = author,
        timestamp = timestamp
    )
}


