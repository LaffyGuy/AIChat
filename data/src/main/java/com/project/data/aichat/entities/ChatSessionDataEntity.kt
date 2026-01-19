package com.project.data.aichat.entities

import com.project.core.data.database.model.ChatSessionEntity

data class ChatSessionDataEntity(
    val id: Long,
    val title: String,
//    val lastMessage: String
)



fun ChatSessionDataEntity.toChatSessionEntity(): ChatSessionEntity {
    return ChatSessionEntity(
        id = id,
        title = title,
//        lastMessage = lastMessage
    )
}
