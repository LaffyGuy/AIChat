package com.project.data.aichat.entities

import com.project.core.data.database.model.ChatSessionEntity
import java.time.LocalDate
import java.time.ZoneId

data class ChatSessionDataEntity(
    val id: Long,
    val title: String,
    val createdAt: LocalDate
)



fun ChatSessionDataEntity.toChatSessionEntity(): ChatSessionEntity {
    return ChatSessionEntity(
        id = id,
        title = title,
        createdAt = createdAt
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()
//        lastMessage = lastMessage
    )
}
