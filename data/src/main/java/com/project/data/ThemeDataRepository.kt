package com.project.data

import kotlinx.coroutines.flow.Flow

interface ThemeDataRepository {

    fun getTheme(): Flow<Boolean>

    suspend fun saveTheme(isDark: Boolean)

}