package com.project.glue.main

import com.project.data.AIChatDataRepository
import com.project.features.main.domain.repositories.AIChatRepository
import javax.inject.Inject

class MainAIRepository @Inject constructor(private val aiChatDataRepository: AIChatDataRepository): AIChatRepository {

    override suspend fun generateResponse(prompt: String): String {
        return aiChatDataRepository.generateResponse(prompt)
    }

    override suspend fun getRecipeResponse(prompt: String): String {
        return aiChatDataRepository.generateRecipe(prompt)
    }
}