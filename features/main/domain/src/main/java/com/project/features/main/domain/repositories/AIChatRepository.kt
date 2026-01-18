package com.project.features.main.domain.repositories

import com.project.features.main.domain.entities.ChatMessage
import com.project.features.main.domain.entities.ChatSession
import kotlinx.coroutines.flow.Flow

interface AIChatRepository {
//
//    suspend fun getRecipeResponse(prompt: String): String

    fun getAiResponse(history: List<ChatMessage>, prompt: String): Flow<String>

    suspend fun saveNewChat(chatSession: ChatSession): Long


}