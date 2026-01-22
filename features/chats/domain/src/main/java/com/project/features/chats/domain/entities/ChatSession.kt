package com.project.features.chats.domain.entities

import java.time.LocalDate

data class ChatSession(
    val id: Long,
    val title: String,
    val isFavorite: Boolean,
    val createdAt: LocalDate
)
