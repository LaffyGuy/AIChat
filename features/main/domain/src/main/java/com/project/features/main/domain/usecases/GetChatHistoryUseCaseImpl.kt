package com.project.features.main.domain.usecases

import com.project.features.main.domain.GetChatHistoryUseCase
import com.project.features.main.domain.entities.ChatMessage
import com.project.features.main.domain.repositories.AIChatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChatHistoryUseCaseImpl @Inject constructor(
    private val aiChatRepository: AIChatRepository
): GetChatHistoryUseCase {

    override fun invoke(chatId: Long): Flow<List<ChatMessage>> {
        return aiChatRepository.getMessages(chatId)
    }
}