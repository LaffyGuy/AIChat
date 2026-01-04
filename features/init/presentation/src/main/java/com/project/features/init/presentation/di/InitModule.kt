package com.project.features.init.presentation.di

import com.project.features.init.domain.GetKeyFeatureUseCase
import com.project.features.init.domain.usecases.GetKeyFeatureUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface InitModule {

    @Binds
    fun bindGetKeyFeatureUseCase(
        impl: GetKeyFeatureUseCaseImpl
    ): GetKeyFeatureUseCase

}