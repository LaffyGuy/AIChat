package com.project.features.about.presentation.di

import com.project.features.about.domain.ChangeLanguageUseCase
import com.project.features.about.domain.ChangeThemeUseCase
import com.project.features.about.domain.GetLanguageSettingsUseCase
import com.project.features.about.domain.GetThemeSettingsUseCase
import com.project.features.about.domain.usecases.ChangeLanguageUseCaseImpl
import com.project.features.about.domain.usecases.ChangeThemeUseCaseImpl
import com.project.features.about.domain.usecases.GetLanguageSettingsUseCaseImpl
import com.project.features.about.domain.usecases.GetThemeSettingsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AboutModule {

    @Binds
    fun bindChangeThemeUseCase(
        impl: ChangeThemeUseCaseImpl
    ): ChangeThemeUseCase

    @Binds
    fun bindGetThemeSettingsUseCase(
        impl: GetThemeSettingsUseCaseImpl
    ): GetThemeSettingsUseCase

    @Binds
    fun bindChangeLanguageUseCase(
        impl: ChangeLanguageUseCaseImpl
    ): ChangeLanguageUseCase

    @Binds
    fun bindGetLanguageSettingsUseCase(
        impl: GetLanguageSettingsUseCaseImpl
    ): GetLanguageSettingsUseCase

}