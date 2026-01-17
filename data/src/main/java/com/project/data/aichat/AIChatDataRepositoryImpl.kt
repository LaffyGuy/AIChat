package com.project.data.aichat

import com.project.core.data.aidata.AIRemoteDataSourceImpl
import com.project.data.AIChatDataRepository
import javax.inject.Inject

class AIChatDataRepositoryImpl @Inject constructor(private val aiRemoteDataSource: AIRemoteDataSourceImpl): AIChatDataRepository {
    override suspend fun generateResponse(prompt: String): String {
       return aiRemoteDataSource.generateResponse(prompt)
    }

    override suspend fun generateRecipe(prompt: String): String {
        return aiRemoteDataSource.generateRecipe(prompt)
    }
}