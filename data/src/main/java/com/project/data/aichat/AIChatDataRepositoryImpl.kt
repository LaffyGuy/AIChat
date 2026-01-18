package com.project.data.aichat

import com.google.firebase.ai.GenerativeModel
import com.google.firebase.ai.type.Content
import com.project.core.data.database.dao.AIChatDao
import com.project.core.data.database.model.ChatSessionEntity
import com.project.data.AIChatDataRepository
import com.project.data.aichat.entities.ChatSessionDataEntity
import javax.inject.Inject

class AIChatDataRepositoryImpl @Inject constructor(
    private val generativeModel: GenerativeModel,
    private val aiChatDao: AIChatDao
): AIChatDataRepository {

//    override suspend fun generateRecipe(prompt: String): String {
//        return aiRemoteDataSource.generateRecipe(prompt)
//    }

    override suspend fun getAiResponse(history: List<Content>, prompt: String): String? {
        val chat = generativeModel.startChat(history)
        val response = chat.sendMessage(prompt)
        return response.text
    }

    override suspend fun saveChat(chatSessionDataEntity: ChatSessionDataEntity): Long {
        return aiChatDao.addNewChat(
            chatSessionEntity =
                ChatSessionEntity(
                    title = chatSessionDataEntity.title
                )
        )
    }

}