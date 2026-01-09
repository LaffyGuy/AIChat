package com.project.glue.init.di

import com.project.features.init.domain.repositories.DateTimeRepository
import com.project.features.init.domain.repositories.KeyFeaturesRepository
import com.project.glue.init.InitDateTimeRepository
import com.project.glue.init.InitKeyFeatureRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface InitModule {

    @Binds
    fun bindDateTimeRepository(
        impl: InitDateTimeRepository
    ): DateTimeRepository

    @Binds
    fun bindInitKeyFeatureRepository(
        impl: InitKeyFeatureRepository
    ): KeyFeaturesRepository

}