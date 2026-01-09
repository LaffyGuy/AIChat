package com.project.data.datetime.di

import com.project.data.DateTimeDataRepository
import com.project.data.datetime.DateTimeDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DateTimeModule {

    @Binds
    fun bindDateTimeDataRepository(
        impl: DateTimeDataRepositoryImpl
    ): DateTimeDataRepository

}