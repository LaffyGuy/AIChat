package com.project.glue.prompts

import com.project.data.PromptSampleDetailsDataRepository
import com.project.features.prompts.domain.entities.PromptSample
import com.project.features.prompts.domain.repositories.PromptSampleDetailsRepository
import com.project.glue.prompts.mappers.toPromptSample
import javax.inject.Inject

class SampleDetailsRepository @Inject constructor(
    private val promptSampleDetailsDataRepository: PromptSampleDetailsDataRepository
): PromptSampleDetailsRepository {

    override suspend fun getPromptSampleDetailsById(promptSampleId: Long): PromptSample {
        return promptSampleDetailsDataRepository.getPromptSampleById(promptSampleId).toPromptSample()
    }

}