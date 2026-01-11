package com.project.features.prompts.domain

import com.project.essentials.LoadResult
import com.project.features.prompts.domain.entities.PromptSample
import kotlinx.coroutines.flow.Flow

interface GetPromptsSampleListUseCase {

    operator fun invoke(): Flow<LoadResult<List<PromptSample>>>

}