package com.project.features.chats.domain

interface UpdateFavoriteStatus {

    suspend operator fun invoke(chatId: Long, isFavorite: Boolean)

}