package com.project.features.about.domain.usecases

import com.project.features.about.domain.ChangeThemeUseCase
import com.project.features.about.domain.repositories.ThemeRepository
import javax.inject.Inject

class ChangeThemeUseCaseImpl @Inject constructor(private val themeRepository: ThemeRepository): ChangeThemeUseCase {

    override suspend operator fun invoke(isDark: Boolean) {
        themeRepository.toggleTheme(isDark)
    }

}