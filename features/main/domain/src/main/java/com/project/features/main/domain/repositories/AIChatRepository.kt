package com.project.features.main.domain.repositories

interface AIChatRepository {

    suspend fun generateResponse(prompt: String): String

}