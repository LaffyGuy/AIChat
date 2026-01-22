package com.project.features.chats.domain

interface UpdateFavoriteStatusUseCase {

    suspend operator fun invoke(chatId: Long, isFavorite: Boolean)

}