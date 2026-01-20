package com.project.domain.entities

import java.time.LocalDate

data class FavoriteChatSession(
    val id: Long,
    val title: String,
    val isFavorite: Boolean,
    val createdAt: LocalDate
)
