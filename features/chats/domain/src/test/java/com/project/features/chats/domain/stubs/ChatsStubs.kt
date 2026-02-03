package com.project.features.chats.domain.stubs

import com.project.features.chats.domain.entities.ChatSession
import java.time.LocalDate

fun createChatsList(): List<ChatSession> {
    return listOf(
        ChatSession(id = 1, title = "Hello", isFavorite = true, createdAt = LocalDate.now())
    )
}