package com.project.data.promptsample.di

import com.project.data.PromptSampleDetailsDataRepository
import com.project.data.PromptsSampleDataRepository
import com.project.data.promptsample.PromptSampleDetailsDataRepositoryImpl
import com.project.data.promptsample.PromptsSampleDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface PromptsSampleModule {

    @Binds
    fun bindPromptsSampleDataRepository(
        impl: PromptsSampleDataRepositoryImpl
    ): PromptsSampleDataRepository

    @Binds
    fun bindPromptSampleDetailsRepository(
        impl: PromptSampleDetailsDataRepositoryImpl
    ): PromptSampleDetailsDataRepository

}