package com.project.glue.init

import com.project.data.KeyFeaturesDataRepository
import com.project.features.init.domain.entities.KeyFeature
import com.project.features.init.domain.repositories.KeyFeaturesRepository
import com.project.glue.init.mappers.toKeyFeature
import java.time.Period
import java.time.ZonedDateTime
import javax.inject.Inject

class InitKeyFeatureRepository @Inject constructor(
    private val keyFeaturesDataRepository: KeyFeaturesDataRepository
): KeyFeaturesRepository {

    override suspend fun getKeyFeatures(): List<KeyFeature> {
        return keyFeaturesDataRepository.getKeyFeatures().map {
            it.toKeyFeature(keyFeaturesDataRepository.getLastDisplayTime(it.id))
        }
    }

    override suspend fun getDisplayPeriod(): Period {
        return keyFeaturesDataRepository.getDisplayPeriod()
    }

    override suspend fun saveDisplayTime(
        keyFeatures: List<KeyFeature>,
        displayTime: ZonedDateTime
    ) {
        keyFeaturesDataRepository.saveLastDisplayTime(keyFeatures.first().id, displayTime)
    }
}