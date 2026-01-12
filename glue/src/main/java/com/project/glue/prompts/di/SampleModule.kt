package com.project.glue.prompts.di

import com.project.features.prompts.domain.repositories.PromptSampleDetailsRepository
import com.project.features.prompts.domain.repositories.PromptsSampleRepository
import com.project.glue.prompts.SampleDetailsRepository
import com.project.glue.prompts.SamplesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SampleModule {

    @Binds
    fun bindPromptsSampleRepository(
        impl: SamplesRepository
    ): PromptsSampleRepository


    @Binds
    fun bindPromptSampleDetailsRepository(
         impl: SampleDetailsRepository
    ): PromptSampleDetailsRepository
}