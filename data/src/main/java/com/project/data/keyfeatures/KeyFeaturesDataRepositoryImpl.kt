package com.project.data.keyfeatures

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.project.data.KeyFeaturesDataRepository
import com.project.data.keyfeatures.entities.KeyFeatureDataEntity
import com.project.essentials.entities.ImageSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import java.time.Period
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class KeyFeaturesDataRepositoryImpl @Inject constructor(
    private val appDataStore: DataStore<Preferences>
): KeyFeaturesDataRepository {

    override suspend fun getKeyFeatures(): List<KeyFeatureDataEntity> {
        return listOf(
            KeyFeatureDataEntity(
                id = 1,
                title = "AI-Powered Conversations",
                description = "Chat with an intelligent AI that understands context, asks follow-up questions, and gives meaningful answers in real time.",
                image = ImageSource.Empty
            ),
            KeyFeatureDataEntity(
                id = 2,
                title = "ASmart Prompt Examples",
                description = "Learn how to write better prompts with ready-to-use examples for different tasks, styles, and goals.",
                image = ImageSource.Empty
            ),
            KeyFeatureDataEntity(
                id = 3,
                title = "Instant Answers",
                description = "Get clear and helpful responses instantly — no waiting, no complex setup, just ask and get results.",
                image = ImageSource.Empty
            ),
        )
    }

    override suspend fun getDisplayPeriod(): Period {
        return Period.ofDays(30)
    }

    override suspend fun saveLastDisplayTime(
        keyFeature: Long,
        displayTime: ZonedDateTime
    ) {
        appDataStore.edit { preferences ->
            preferences[key(keyFeature)] = DateTimeFormatter.ISO_ZONED_DATE_TIME.format(displayTime)
        }
    }

    override suspend fun getLastDisplayTime(keyFeatureId: Long): ZonedDateTime {
        return appDataStore.data.map { preferences ->
            preferences[key(keyFeatureId)]?.let {
                ZonedDateTime.parse(preferences[key(keyFeatureId)])
            }?: ZonedDateTime.of(LocalDateTime.MIN, ZoneOffset.UTC)
        }
            .first()
    }

    private fun key(id: Long): Preferences.Key<String> {
        return stringPreferencesKey(name = "key-features-display-time-$id")
    }

}