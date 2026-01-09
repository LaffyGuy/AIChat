package com.project.data

import com.project.data.keyfeatures.entities.KeyFeatureDataEntity
import java.time.Period
import java.time.ZonedDateTime

interface KeyFeaturesDataRepository {

    suspend fun getKeyFeatures(): List<KeyFeatureDataEntity>

    suspend fun getDisplayPeriod(): Period

    suspend fun saveLastDisplayTime(keyFeature: Long, displayTime: ZonedDateTime)

    suspend fun getLastDisplayTime(keyFeatureId: Long): ZonedDateTime


}