package com.project.data.chats.mappers

import com.project.core.data.database.model.ChatSessionEntity
import com.project.data.aichat.entities.ChatSessionDataEntity

fun ChatSessionEntity.toChatSessionDataEntity(): ChatSessionDataEntity {
    return ChatSessionDataEntity(
        id = id,
        title = title,
//        lastMessage = lastMessage
    )
}