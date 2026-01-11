package com.project.features.prompts.presentation.di

import com.project.features.prompts.domain.GetPromptSampleDetailsByIdUseCase
import com.project.features.prompts.domain.GetPromptsSampleListUseCase
import com.project.features.prompts.domain.usecases.GetPromptSampleDetailsByIdUseCaseImpl
import com.project.features.prompts.domain.usecases.GetPromptsSampleListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface PromptsSampleModule {

    @Binds
    fun bindGetPromptsSampleListUseCase(
        impl: GetPromptsSampleListUseCaseImpl
    ): GetPromptsSampleListUseCase

    @Binds
    fun bindGetPromptSampleDetailsByIdUseCase(
        impl: GetPromptSampleDetailsByIdUseCaseImpl
    ): GetPromptSampleDetailsByIdUseCase

}