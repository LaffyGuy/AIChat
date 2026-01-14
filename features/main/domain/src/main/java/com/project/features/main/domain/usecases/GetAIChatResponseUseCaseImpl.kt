package com.project.features.main.domain.usecases

import com.project.features.main.domain.GetAIChatResponseUseCase
import com.project.features.main.domain.repositories.AIChatRepository
import javax.inject.Inject

class GetAIChatResponseUseCaseImpl @Inject constructor(private val aiChatRepository: AIChatRepository): GetAIChatResponseUseCase {

    override suspend fun invoke(prompt: String): String {
        return aiChatRepository.generateResponse(prompt)
    }
}