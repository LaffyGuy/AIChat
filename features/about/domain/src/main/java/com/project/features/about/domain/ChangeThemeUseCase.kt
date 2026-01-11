package com.project.features.about.domain

import kotlinx.coroutines.flow.Flow

interface ChangeThemeUseCase {

    suspend operator fun invoke(isDark: Boolean)

}