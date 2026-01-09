package com.project.features.init.presentation.di

import com.project.features.init.domain.ShowKeyFeaturesUseCase
import com.project.features.init.domain.usecases.ShowKeyFeatureUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface InitModule {

    @Binds
    fun bindGetKeyFeatureUseCase(
        impl: ShowKeyFeatureUseCaseImpl
    ): ShowKeyFeaturesUseCase

}