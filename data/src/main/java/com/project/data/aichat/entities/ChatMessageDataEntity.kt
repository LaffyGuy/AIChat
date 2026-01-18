package com.project.data.aichat.entities

import com.project.core.data.database.model.ChatMessageEntity
import com.project.essentials.entities.MessageAuthor
import kotlin.Long

data class ChatMessageDataEntity(
    val id: Long,
    val text: String,
    val author: MessageAuthor,
    val timestamp: Long = System.currentTimeMillis(),
)

fun ChatMessageEntity.toChatMessageDataEntity(): ChatMessageDataEntity {
    return ChatMessageDataEntity(
        id = id,
        text = text,
        author = author
    )
}


