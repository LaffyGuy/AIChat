package com.project.data.aichat.di

import com.project.data.AIChatDataRepository
import com.project.data.aichat.AIChatDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AIChatModule {

    @Binds
    fun bindAIChatDataRepository(
        impl: AIChatDataRepositoryImpl
    ): AIChatDataRepository


}