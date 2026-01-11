package com.project.glue.prompts

import com.project.data.PromptSampleDetailsDataRepository
import com.project.features.prompts.domain.entities.PromptSample
import com.project.features.prompts.domain.repositories.PromptSampleDetailsRepository
import com.project.glue.prompts.mappers.toPromptSample

class SampleDetailsRepository (private val promptSampleDetailsDataRepository: PromptSampleDetailsDataRepository): PromptSampleDetailsRepository {

    override suspend fun getPromptSampleDetailsById(promptSampleId: Long): PromptSample {
        return promptSampleDetailsDataRepository.getPromptSampleById(promptSampleId).toPromptSample()
    }

}