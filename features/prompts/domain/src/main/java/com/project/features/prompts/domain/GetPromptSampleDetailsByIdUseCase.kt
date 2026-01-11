package com.project.features.prompts.domain

import com.project.features.prompts.domain.entities.PromptSample

interface GetPromptSampleDetailsByIdUseCase {

    suspend operator fun invoke(promptSampleId: Long): PromptSample

}