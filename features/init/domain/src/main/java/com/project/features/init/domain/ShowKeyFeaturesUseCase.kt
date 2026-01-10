package com.project.features.init.domain

import com.project.features.init.domain.entities.KeyFeature
import kotlinx.coroutines.flow.Flow

interface ShowKeyFeaturesUseCase {

    operator fun invoke(): Flow<Result>

    sealed class Result {

        data class Show(val data: List<KeyFeature>): Result()

        data object Skip: Result()
    }

}