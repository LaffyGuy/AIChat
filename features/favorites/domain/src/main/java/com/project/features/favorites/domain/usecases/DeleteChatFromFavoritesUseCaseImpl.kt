package com.project.features.favorites.domain.usecases

import com.project.features.favorites.domain.DeleteChatFromFavoritesUseCase
import com.project.features.favorites.domain.repositories.FavoriteChatsRepository
import javax.inject.Inject

class DeleteChatFromFavoritesUseCaseImpl @Inject constructor(
    private val favoriteChatsRepository: FavoriteChatsRepository
) : DeleteChatFromFavoritesUseCase {

    override suspend fun invoke(chatId: Long, isFavorite: Boolean) {
        favoriteChatsRepository.deleteChatFromFavorites(chatId, isFavorite)
    }

}