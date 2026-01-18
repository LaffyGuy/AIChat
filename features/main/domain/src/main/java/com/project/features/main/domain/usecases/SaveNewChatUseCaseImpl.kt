package com.project.features.main.domain.usecases

import com.project.features.main.domain.SaveNewChatUseCase
import com.project.features.main.domain.entities.ChatSession
import com.project.features.main.domain.repositories.AIChatRepository
import javax.inject.Inject

class SaveNewChatUseCaseImpl @Inject constructor(private val aiChatRepository: AIChatRepository): SaveNewChatUseCase {

    override suspend fun invoke(chatSession: ChatSession): Long {
        return aiChatRepository.saveNewChat(chatSession)
    }

}