package com.project.features.prompts.domain.usecases

import com.project.essentials.logger.Logger
import com.project.features.prompts.domain.GetPromptSampleDetailsByIdUseCase
import com.project.features.prompts.domain.entities.PromptSample
import com.project.features.prompts.domain.repositories.PromptSampleDetailsRepository
import javax.inject.Inject

class GetPromptSampleDetailsByIdUseCaseImpl @Inject constructor(
    private val promptSampleDetailsRepository: PromptSampleDetailsRepository
): GetPromptSampleDetailsByIdUseCase {

    override suspend fun invoke(promptSampleId: Long): PromptSample {
        val prompt = promptSampleDetailsRepository.getPromptSampleDetailsById(promptSampleId)
        Logger.d("AAAAA - prompt UseCase - $prompt")
        return prompt
//        promptSampleDetailsRepository.getPromptSampleDetailsById(promptSampleId)
    }

}