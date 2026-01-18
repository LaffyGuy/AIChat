package com.project.features.main.domain

import com.project.features.main.domain.entities.ChatMessage
import kotlinx.coroutines.flow.Flow

interface GetAIChatResponseUseCase {

    suspend operator fun invoke(history: List<ChatMessage>, prompt: String): Flow<String>

}