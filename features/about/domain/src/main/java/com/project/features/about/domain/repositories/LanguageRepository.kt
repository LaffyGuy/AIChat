package com.project.features.about.domain.repositories

import kotlinx.coroutines.flow.Flow

interface LanguageRepository {

    fun getLanguage(): Flow<String>

    suspend fun changeLanguage(language: String)

}