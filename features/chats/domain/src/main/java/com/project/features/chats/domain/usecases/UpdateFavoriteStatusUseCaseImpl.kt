package com.project.features.chats.domain.usecases

import com.project.features.chats.domain.UpdateFavoriteStatusUseCase
import com.project.features.chats.domain.repositories.ChatsRepository
import javax.inject.Inject

class UpdateFavoriteStatusUseCaseImpl @Inject constructor(
    private val chatsRepository: ChatsRepository
): UpdateFavoriteStatusUseCase {

    override suspend fun invoke(chatId: Long, isFavorite: Boolean) {
        chatsRepository.updateFavoriteChatStatus(chatId, isFavorite)
    }

}