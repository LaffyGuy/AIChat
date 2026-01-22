package com.project.features.main.domain.entities

import java.time.LocalDate

data class MainChatSession(
    val id: Long,
    val title: String,
    val isFavorite: Boolean,
    val createdAt: LocalDate
)
