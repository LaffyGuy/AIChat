package com.project.data.language

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.project.data.LanguageDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LanguageDataRepositoryImpl @Inject constructor(private val appDataStore: DataStore<Preferences>): LanguageDataRepository {

    override fun getLanguage(): Flow<String> {
        return appDataStore.data.map { preferences ->
            preferences[LANGUAGE_SETTINGS] ?: "en"
        }
    }

    override suspend fun saveLanguage(language: String) {
        appDataStore.edit { preferences ->
            preferences[LANGUAGE_SETTINGS] = language
        }
    }

    private val LANGUAGE_SETTINGS = stringPreferencesKey("language-settings")
}