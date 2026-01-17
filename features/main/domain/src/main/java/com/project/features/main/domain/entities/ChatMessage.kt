package com.project.features.main.domain.entities

import java.util.UUID

data class ChatMessage(
    val id: String = UUID.randomUUID().toString(),
    val text: String,
    val author: MessageAuthor,
    val timestamp: Long = System.currentTimeMillis()
)
