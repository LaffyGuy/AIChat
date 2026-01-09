package com.project.features.init.domain.usecases

import com.project.features.init.domain.ShowKeyFeaturesUseCase
import com.project.features.init.domain.ShowKeyFeaturesUseCase.*
import com.project.features.init.domain.entities.KeyFeature
import com.project.features.init.domain.repositories.DateTimeRepository
import com.project.features.init.domain.repositories.KeyFeaturesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ShowKeyFeatureUseCaseImpl @Inject constructor(
    private val keyFeaturesRepository: KeyFeaturesRepository,
    private val dateTimeRepository: DateTimeRepository
): ShowKeyFeaturesUseCase {
    override fun invoke(): Flow<Result> = flow {
        if (shouldShowKeyFeature()) {
            val keyFeatures = getKeyFeatures()
            emit(Result.Show(keyFeatures))
            saveDisplayTime(keyFeatures)
        } else {
            emit(Result.Skip)
        }
    }

    private suspend fun shouldShowKeyFeature(): Boolean {
        val period = keyFeaturesRepository.getDisplayPeriod()
        val lastDisplayTime = keyFeaturesRepository.getKeyFeatures().maxOf { it.lastDisplayTime }
        val now = dateTimeRepository.now()
        return lastDisplayTime + period < now
    }

    private suspend fun getKeyFeatures(): List<KeyFeature> {
        return keyFeaturesRepository.getKeyFeatures()
    }

    private suspend fun saveDisplayTime(keyFeatures: List<KeyFeature>) {
        val now = dateTimeRepository.now()
        keyFeaturesRepository.saveDisplayTime(keyFeatures, now)
    }


}