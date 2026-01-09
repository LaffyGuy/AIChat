package com.project.data.keyfeatures.di

import com.project.data.KeyFeaturesDataRepository
import com.project.data.keyfeatures.KeyFeaturesDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface KeyFeaturesDataModule {

    @Binds
    fun bindKeyFeatureDataRepository(
        impl: KeyFeaturesDataRepositoryImpl
    ): KeyFeaturesDataRepository



}