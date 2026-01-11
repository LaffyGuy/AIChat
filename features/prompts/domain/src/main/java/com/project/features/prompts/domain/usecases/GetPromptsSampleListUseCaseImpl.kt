package com.project.features.prompts.domain.usecases

import com.project.essentials.LoadResult
import com.project.features.prompts.domain.GetPromptsSampleListUseCase
import com.project.features.prompts.domain.entities.PromptSample
import com.project.features.prompts.domain.repositories.PromptsSampleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPromptsSampleListUseCaseImpl @Inject constructor(
    private val promptsSampleRepository: PromptsSampleRepository
): GetPromptsSampleListUseCase {

    override fun invoke(): Flow<LoadResult<List<PromptSample>>> {
        return promptsSampleRepository.getPromptSamplesList()
    }

}