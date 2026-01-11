package com.project.data.theme

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.project.data.ThemeDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ThemeDataRepositoryImpl @Inject constructor(private val appDataStore: DataStore<Preferences>): ThemeDataRepository {

    override fun getTheme(): Flow<Boolean> {
        return appDataStore.data.map { preferences ->
            preferences[THEME_KEY] ?: false
        }
    }

    override suspend fun saveTheme(isDark: Boolean) {
        appDataStore.edit { preferences ->
            preferences[THEME_KEY] = isDark
        }
    }

   private val THEME_KEY = booleanPreferencesKey("is_dark_theme")

}