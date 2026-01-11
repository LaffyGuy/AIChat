package com.project.features.prompts.domain.repositories

import com.project.features.prompts.domain.entities.PromptSample

interface PromptSampleDetailsRepository {

    suspend fun getPromptSampleDetailsById(promptSampleId: Long): PromptSample

}