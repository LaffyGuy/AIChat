package com.project.features.favorites.domain

interface DeleteChatFromFavoritesUseCase {

    suspend operator fun invoke(chatId: Long, isFavorite: Boolean)

}