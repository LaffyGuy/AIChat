package com.project.data

import com.project.data.promptsample.entities.PromptSampleDataEntity

interface PromptSampleDetailsDataRepository {

    suspend fun getPromptSampleById(promptSampleId: Long): PromptSampleDataEntity
}