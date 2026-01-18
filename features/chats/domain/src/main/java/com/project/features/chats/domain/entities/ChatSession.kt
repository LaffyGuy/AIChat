package com.project.features.chats.domain.entities

data class ChatSession(
    val id: Long,
    val title: String,
    val lastMessage: String
)
