package com.project.common_android.di

import com.project.common_android.handler.AndroidExceptionHandler
import com.project.common_android.logger.AndroidLogger
import com.project.common_android.resources.CoreStringProviderImpl
import com.project.essentials.exceptions.ExceptionToMessageMapper
import com.project.essentials.exceptions.mapper.DefaultExceptionToMessageMapper
import com.project.essentials.handler.ExceptionHandler
import com.project.essentials.logger.Logger
import com.project.essentials.resources.CoreStringProvider
import com.project.essentials.resources.StringProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface CommonAndroidModule {

    @Binds
    fun bindLogger(
        impl: AndroidLogger
    ): Logger

    @Binds
    fun bindExceptionHandler(
        impl: AndroidExceptionHandler
    ): ExceptionHandler

    @Binds
    @IntoMap
    @ClassKey(CoreStringProvider::class)
    fun bindCoreStringProvider(
        impl: CoreStringProviderImpl
    ): StringProvider

    @Binds
    fun bindExceptionToMessageMapper(
        impl: DefaultExceptionToMessageMapper
    ): ExceptionToMessageMapper

}