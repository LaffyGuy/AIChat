package com.project.features.about.domain

interface ChangeThemeUseCase {

    suspend operator fun invoke(isDark: Boolean)

}