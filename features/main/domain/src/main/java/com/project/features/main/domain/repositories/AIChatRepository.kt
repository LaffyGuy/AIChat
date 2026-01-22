package com.project.features.main.domain.repositories

import com.project.essentials.entities.MessageAuthor
import com.project.features.main.domain.entities.ChatMessage
import com.project.features.main.domain.entities.MainChatSession
import kotlinx.coroutines.flow.Flow

interface AIChatRepository {

    fun getAiResponse(history: List<ChatMessage>, prompt: String): Flow<String>

    suspend fun saveNewChat(chatSession: MainChatSession): Long

    fun getMessages(chatId: Long): Flow<List<ChatMessage>>

    suspend fun saveMessage(chatId: Long, text: String, author: MessageAuthor)

}