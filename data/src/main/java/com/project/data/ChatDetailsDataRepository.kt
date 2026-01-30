package com.project.data

import com.google.firebase.ai.type.Content
import com.project.data.aichat.entities.ChatMessageDataEntity
import com.project.data.aichat.entities.ChatSessionDataEntity
import com.project.essentials.entities.MessageAuthor
import kotlinx.coroutines.flow.Flow

interface ChatDetailsDataRepository {

    suspend fun getAiResponse(history: List<Content>, prompt: String): String?

    suspend fun saveChat(chatSessionDataEntity: ChatSessionDataEntity): Long

    fun getMessages(chatId: Long): Flow<List<ChatMessageDataEntity>>

    suspend fun saveMessage(chatMessageDataEntity: ChatMessageDataEntity)

}