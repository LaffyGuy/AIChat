package com.project.data.language.di

import com.project.data.LanguageDataRepository
import com.project.data.language.LanguageDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LanguageDataModule {

    @Binds
    fun bindLanguageDataRepository(
        impl: LanguageDataRepositoryImpl
    ): LanguageDataRepository

}