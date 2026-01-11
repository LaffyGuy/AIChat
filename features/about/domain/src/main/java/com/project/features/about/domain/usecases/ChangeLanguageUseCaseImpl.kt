package com.project.features.about.domain.usecases

import com.project.features.about.domain.ChangeLanguageUseCase
import com.project.features.about.domain.repositories.LanguageRepository
import javax.inject.Inject

class ChangeLanguageUseCaseImpl @Inject constructor(private val languageRepository: LanguageRepository): ChangeLanguageUseCase {

    override suspend fun invoke(language: String) {
        languageRepository.changeLanguage(language)
    }

}