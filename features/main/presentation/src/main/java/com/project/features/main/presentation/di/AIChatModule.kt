package com.project.features.main.presentation.di

import com.project.features.main.domain.GetAIChatResponseUseCase
import com.project.features.main.domain.GetRecipeAIResponseUseCase
import com.project.features.main.domain.usecases.GetAIChatResponseUseCaseImpl
import com.project.features.main.domain.usecases.GetRecipeAIResponseUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AIChatModule {

    @Binds
    fun bindGetAIChatResponseUseCase(
        impl: GetAIChatResponseUseCaseImpl
    ): GetAIChatResponseUseCase

    @Binds
    fun bindGetRecipeAIResponseUseCase(
        impl: GetRecipeAIResponseUseCaseImpl
    ): GetRecipeAIResponseUseCase

}