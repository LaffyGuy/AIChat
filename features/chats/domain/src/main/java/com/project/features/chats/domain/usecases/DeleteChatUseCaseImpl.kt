package com.project.features.chats.domain.usecases

import com.project.features.chats.domain.DeleteChatUseCase
import com.project.features.chats.domain.effects.ChatsUserChoices
import com.project.features.chats.domain.repositories.ChatsRepository
import javax.inject.Inject

class DeleteChatUseCaseImpl @Inject constructor(
    private val chatsRepository: ChatsRepository,
    private val userChoices: ChatsUserChoices
): DeleteChatUseCase {

    override suspend fun invoke(chatId: Long) {
        val chat = chatsRepository.getChatById(chatId)
        val isConfirmed = userChoices.confirmChatDeletion(chat)
        if (isConfirmed) {
            chatsRepository.deleteChat(chatId)
        }
    }

}