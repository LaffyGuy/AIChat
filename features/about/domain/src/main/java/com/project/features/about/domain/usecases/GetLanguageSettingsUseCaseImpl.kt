package com.project.features.about.domain.usecases

import com.project.features.about.domain.GetLanguageSettingsUseCase
import com.project.features.about.domain.repositories.LanguageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLanguageSettingsUseCaseImpl @Inject constructor(private val languageRepository: LanguageRepository): GetLanguageSettingsUseCase {

    override fun invoke(): Flow<String> {
        return languageRepository.getLanguage()
    }
}