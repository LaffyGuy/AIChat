package com.project.data

import com.google.firebase.ai.type.Content
import com.project.data.aichat.entities.ChatSessionDataEntity

interface AIChatDataRepository {

//    suspend fun generateRecipe(prompt: String): String

    suspend fun getAiResponse(history: List<Content>, prompt: String): String?

    suspend fun saveChat(chatSessionDataEntity: ChatSessionDataEntity): Long


}