package com.project.glue.favorites.mappers

import com.project.data.aichat.entities.ChatSessionDataEntity
import com.project.features.favorites.domain.entities.FavoriteChatSession

fun ChatSessionDataEntity.toFavoriteChatSession(): FavoriteChatSession {
    return FavoriteChatSession(
        id = id,
        title = title,
        isFavorite = isFavorite,
        createdAt = createdAt
    )
}