package com.project.glue.about.di

import com.project.features.about.domain.repositories.LanguageRepository
import com.project.features.about.domain.repositories.ThemeRepository
import com.project.glue.about.AboutLanguageRepository
import com.project.glue.about.AboutThemeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AboutThemeModule {

    @Binds
    fun bindThemeRepository(
        impl: AboutThemeRepository
    ): ThemeRepository

    @Binds
    fun bindLanguageRepository(
        impl: AboutLanguageRepository
    ): LanguageRepository
}