package com.project.glue.main.di

import com.project.features.main.domain.repositories.AIChatRepository
import com.project.glue.main.MainAIRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MainAIModule {

    @Binds
    fun bindAIChatRepository(
        impl: MainAIRepository
    ): AIChatRepository

}