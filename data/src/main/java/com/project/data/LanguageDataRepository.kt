package com.project.data

import kotlinx.coroutines.flow.Flow

interface LanguageDataRepository {

    fun getLanguage(): Flow<String>

    suspend fun saveLanguage(language: String)

}