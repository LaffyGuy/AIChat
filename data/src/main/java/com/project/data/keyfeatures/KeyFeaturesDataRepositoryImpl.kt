package com.project.data.keyfeatures

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.project.data.KeyFeaturesDataRepository
import com.project.data.R
import com.project.data.keyfeatures.entities.KeyFeatureDataEntity
import com.project.essentials.entities.ImageSource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import java.time.Period
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class KeyFeaturesDataRepositoryImpl @Inject constructor(
    private val appDataStore: DataStore<Preferences>,
    @ApplicationContext private val context: Context
): KeyFeaturesDataRepository {

    override suspend fun getKeyFeatures(): List<KeyFeatureDataEntity> {
        return listOf(
            KeyFeatureDataEntity(
                id = 1,
                title = context.getString(R.string.data_features1_title),
                description = context.getString(R.string.data_feature1_description),
                image = ImageSource.DrawableRes(R.drawable.feature_ai)
            ),
            KeyFeatureDataEntity(
                id = 2,
                title = context.getString(R.string.data_feature2_title),
                description = context.getString(R.string.data_feature2_description),
                image = ImageSource.DrawableRes(R.drawable.feature_smart)
            ),
            KeyFeatureDataEntity(
                id = 3,
                title = context.getString(R.string.data_feature3_title),
                description = context.getString(R.string.data_feature3_description),
                image = ImageSource.DrawableRes(R.drawable.feature_answers)
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