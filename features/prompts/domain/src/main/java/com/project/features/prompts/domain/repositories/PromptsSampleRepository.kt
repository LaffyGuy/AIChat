package com.project.features.prompts.domain.repositories

import com.project.essentials.LoadResult
import com.project.features.prompts.domain.entities.PromptSample
import kotlinx.coroutines.flow.Flow

interface PromptsSampleRepository {

    fun getPromptSamplesList(): Flow<LoadResult<List<PromptSample>>>

}