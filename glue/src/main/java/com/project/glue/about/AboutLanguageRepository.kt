package com.project.glue.about

import com.project.data.LanguageDataRepository
import com.project.features.about.domain.repositories.LanguageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AboutLanguageRepository @Inject constructor(private val languageDataRepository: LanguageDataRepository): LanguageRepository {
    override fun getLanguage(): Flow<String> {
        return languageDataRepository.getLanguage()
    }

    override suspend fun changeLanguage(language: String) {
        languageDataRepository.saveLanguage(language)
    }
}