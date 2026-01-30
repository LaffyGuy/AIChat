package com.project.data.aichat

import com.google.firebase.ai.GenerativeModel
import com.google.firebase.ai.type.Content
import com.project.core.data.database.dao.AIChatDao
import com.project.data.ChatDetailsDataRepository
import com.project.data.aichat.entities.ChatMessageDataEntity
import com.project.data.aichat.entities.ChatSessionDataEntity
import com.project.data.aichat.entities.toChatMessageDataEntity
import com.project.data.aichat.entities.toChatMessageEntity
import com.project.data.aichat.entities.toChatSessionEntity
import com.project.essentials.entities.MessageAuthor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ChatDetailsDataRepositoryImpl @Inject constructor(
    private val generativeModel: GenerativeModel,
    private val aiChatDao: AIChatDao
): ChatDetailsDataRepository {

    override suspend fun getAiResponse(history: List<Content>, prompt: String): String? {
        val chat = generativeModel.startChat(history)
        val response = chat.sendMessage(prompt)
        return response.text
    }

    override suspend fun saveChat(chatSessionDataEntity: ChatSessionDataEntity): Long {
        val chatSessionEntity = chatSessionDataEntity.toChatSessionEntity()
        return aiChatDao.addNewChat(chatSessionEntity)
    }

    override fun getMessages(chatId: Long): Flow<List<ChatMessageDataEntity>> {
        return aiChatDao.getMessagesByChatId(chatId).map { list ->
            list.map { it.toChatMessageDataEntity() }
        }
    }

    override suspend fun saveMessage(chatMessageDataEntity: ChatMessageDataEntity) {
        aiChatDao.insertMessage(
            chatMessageDataEntity.toChatMessageEntity()
        )
    }

}