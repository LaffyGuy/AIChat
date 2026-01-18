package com.project.glue.main

import com.google.firebase.ai.type.content
import com.project.data.AIChatDataRepository
import com.project.data.aichat.entities.ChatSessionDataEntity
import com.project.essentials.entities.MessageAuthor
import com.project.features.main.domain.entities.ChatMessage
import com.project.features.main.domain.entities.ChatSession
import com.project.features.main.domain.repositories.AIChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainAIRepository @Inject constructor(private val aiChatDataRepository: AIChatDataRepository): AIChatRepository {

//    override suspend fun getRecipeResponse(prompt: String): String {
//        return aiChatDataRepository.generateRecipe(prompt)
//    }

    override fun getAiResponse(history: List<ChatMessage>, prompt: String): Flow<String> = flow {
        val firebaseHistory = history
            .map { msg ->
                val role = when (msg.author) {
                    MessageAuthor.USER -> "user"
                    MessageAuthor.AI -> "model"
                }

                content(role = role) { text(msg.text) }
            }

        val response = aiChatDataRepository.getAiResponse(firebaseHistory, prompt)

        if (response != null) {
            emit(response)
        } else {
            throw Exception("ШІ повернув порожню відповідь")
        }
    }

    override suspend fun saveNewChat(chatSession: ChatSession): Long {
        return aiChatDataRepository.saveChat(
            chatSessionDataEntity = ChatSessionDataEntity(
                id = chatSession.id,
                title = chatSession.title
            )
        )
    }
}