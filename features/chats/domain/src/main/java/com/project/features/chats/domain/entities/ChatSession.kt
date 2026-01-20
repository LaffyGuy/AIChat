package com.project.features.chats.domain.entities

import java.time.LocalDate

data class ChatSession(
    val id: Long,
    val title: String,
    val createAt: LocalDate
//    val lastMessage: String
)
