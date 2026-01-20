package com.project.data.chats.mappers

import com.project.core.data.database.model.ChatSessionEntity
import com.project.data.aichat.entities.ChatSessionDataEntity
import java.time.Instant
import java.time.ZoneId

fun ChatSessionEntity.toChatSessionDataEntity(): ChatSessionDataEntity {
    return ChatSessionDataEntity(
        id = id,
        title = title,
        createdAt = Instant
            .ofEpochMilli(createdAt)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    )
}