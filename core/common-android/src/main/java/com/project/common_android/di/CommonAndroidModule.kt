package com.project.common_android.di

import com.project.common_android.logger.AndroidLogger
import com.project.common_android.resources.AndroidStringResources
import com.project.essentials.logger.Logger
import com.project.essentials.resources.StringResources
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CommonAndroidModule {

    @Binds
    fun bindLogger(
        impl: AndroidLogger
    ): Logger

    @Binds
    fun bindStringResources(
        impl: AndroidStringResources
    ): StringResources

}