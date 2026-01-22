package com.project.glue.chats.mappers

import com.project.data.aichat.entities.ChatSessionDataEntity
import com.project.features.chats.domain.entities.ChatSession

fun ChatSessionDataEntity.toChatSession(): ChatSession {
    return ChatSession(
        id =  id,
        title = title,
        isFavorite = isFavorite,
        createdAt = createdAt
//        lastMessage = lastMessage
    )
}