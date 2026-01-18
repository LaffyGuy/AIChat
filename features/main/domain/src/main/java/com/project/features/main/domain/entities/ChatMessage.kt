package com.project.features.main.domain.entities

import com.project.essentials.entities.MessageAuthor
import java.util.UUID

data class ChatMessage(
    val id: Long,
    val text: String,
    val author: MessageAuthor,
    val timestamp: Long = System.currentTimeMillis()
)
