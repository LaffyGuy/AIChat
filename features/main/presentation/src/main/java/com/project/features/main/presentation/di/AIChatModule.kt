package com.project.features.main.presentation.di

import com.project.essentials.resources.CoreStringProvider
import com.project.features.main.domain.GetAIChatResponseUseCase
import com.project.features.main.domain.GetChatHistoryUseCase
import com.project.features.main.domain.SaveMessageUseCase
import com.project.features.main.domain.SaveNewChatUseCase
import com.project.features.main.domain.resources.MainStringProvider
import com.project.features.main.domain.usecases.GetAIChatResponseUseCaseImpl
import com.project.features.main.domain.usecases.GetChatHistoryUseCaseImpl
import com.project.features.main.domain.usecases.SaveMessageUseCaseImpl
import com.project.features.main.domain.usecases.SaveNewChatUseCaseImpl
import com.project.features.main.presentation.resources.MainStringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface AIChatModule {

    @Binds
    fun bindGetAIChatResponseUseCase(
        impl: GetAIChatResponseUseCaseImpl
    ): GetAIChatResponseUseCase

    @Binds
    fun bindSaveNewChatUseCase(
        impl: SaveNewChatUseCaseImpl
    ): SaveNewChatUseCase

    @Binds
    fun bindGetChatHistoryUseCase(
        impl: GetChatHistoryUseCaseImpl
    ): GetChatHistoryUseCase

    @Binds
    fun bingSaveMessageUseCase(
        impl: SaveMessageUseCaseImpl
    ): SaveMessageUseCase

    @Binds
    fun bindMainStringProvider(
        impl: MainStringProviderImpl
    ): MainStringProvider

}