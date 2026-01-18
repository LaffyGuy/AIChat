package com.project.features.main.domain.usecases

import com.project.features.main.domain.GetAIChatResponseUseCase
import com.project.features.main.domain.entities.ChatMessage
import com.project.features.main.domain.repositories.AIChatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAIChatResponseUseCaseImpl @Inject constructor(private val aiChatRepository: AIChatRepository): GetAIChatResponseUseCase {

    override suspend fun invoke(history: List<ChatMessage>, prompt: String): Flow<String> {
        return aiChatRepository.getAiResponse(history, prompt)
    }

}