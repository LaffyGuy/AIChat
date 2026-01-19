package com.project.features.main.domain.usecases

import com.project.essentials.entities.MessageAuthor
import com.project.features.main.domain.SaveMessageUseCase
import com.project.features.main.domain.repositories.AIChatRepository
import javax.inject.Inject

class SaveMessageUseCaseImpl @Inject constructor(private val aiChatRepository: AIChatRepository): SaveMessageUseCase {

    override suspend fun invoke(chatId: Long, text: String, author: MessageAuthor) {
       aiChatRepository.saveMessage(chatId, text, author)
    }
}