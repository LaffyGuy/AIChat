package com.project.aichat.di

import com.project.aichat.mapper.DefaultExceptionToMessageMapper
import com.project.essentials.exceptions.ExceptionToMessageMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ExceptionToMessageMapperModule {

    @Binds
    fun bindExceptionToMessageMapper(
        impl: DefaultExceptionToMessageMapper
    ): ExceptionToMessageMapper


}