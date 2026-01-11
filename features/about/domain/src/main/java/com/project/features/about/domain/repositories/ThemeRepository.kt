package com.project.features.about.domain.repositories

import kotlinx.coroutines.flow.Flow

interface ThemeRepository {

    fun getTheme(): Flow<Boolean>

    suspend fun toggleTheme(isDark: Boolean)

}
