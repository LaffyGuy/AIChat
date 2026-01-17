package com.project.data

interface AIChatDataRepository {

    suspend fun generateResponse(prompt: String): String?

    suspend fun generateRecipe(prompt: String): String


}