package com.project.features.about.domain.usecases

import com.project.features.about.domain.GetThemeSettingsUseCase
import com.project.features.about.domain.repositories.ThemeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetThemeSettingsUseCaseImpl @Inject constructor(private val themeRepository: ThemeRepository): GetThemeSettingsUseCase {

    override fun invoke(): Flow<Boolean> {
        return themeRepository.getTheme()
    }

}