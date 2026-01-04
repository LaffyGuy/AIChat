package com.project.features.init.domain.usecases

import com.project.essentials.LoadResult
import com.project.essentials.exceptions.UnknownException
import com.project.features.init.domain.GetKeyFeatureUseCase
import com.project.features.init.domain.entities.KeyFeature
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetKeyFeatureUseCaseImpl @Inject constructor(): GetKeyFeatureUseCase {
    override fun invoke(): Flow<LoadResult<KeyFeature>> {
        return flow {
            delay(2000)
            try {
                emit(LoadResult.Success(KeyFeature(id = 1, title = "Hello", description = "Ahahahaha")))
            } catch (e: Exception) {
                emit(LoadResult.Error(e))
            }
        }
    }
}