package com.project.features.init.domain

import com.project.essentials.LoadResult
import com.project.features.init.domain.entities.KeyFeature
import kotlinx.coroutines.flow.Flow

interface GetKeyFeatureUseCase {

    operator fun invoke(): Flow<LoadResult<KeyFeature>>

}