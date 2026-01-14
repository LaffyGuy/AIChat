package com.project.data

interface AIChatDataRepository {

    suspend fun generateResponse(prompt: String): String

}