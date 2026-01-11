package com.project.data

import com.project.data.promptsample.entities.PromptSampleDataEntity
import com.project.essentials.LoadResult
import kotlinx.coroutines.flow.Flow

interface PromptsSampleDataRepository {

    fun getPromptSamplesList(): Flow<LoadResult<List<PromptSampleDataEntity>>>

}