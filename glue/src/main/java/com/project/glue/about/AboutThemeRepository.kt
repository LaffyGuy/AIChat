package com.project.glue.about

import com.project.data.ThemeDataRepository
import com.project.features.about.domain.repositories.ThemeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AboutThemeRepository @Inject constructor(private val themeDataRepository: ThemeDataRepository): ThemeRepository {

    override fun getTheme(): Flow<Boolean> {
        return themeDataRepository.getTheme()
    }

    override suspend fun toggleTheme(isDark: Boolean) {
        themeDataRepository.saveTheme(isDark)
    }
}