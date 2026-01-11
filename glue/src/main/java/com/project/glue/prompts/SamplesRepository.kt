package com.project.glue.prompts

import com.project.data.PromptsSampleDataRepository
import com.project.essentials.LoadResult
import com.project.features.prompts.domain.entities.PromptSample
import com.project.features.prompts.domain.repositories.PromptsSampleRepository
import com.project.glue.prompts.mappers.toPromptSample
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SamplesRepository @Inject constructor(private val promptsSampleDataRepository: PromptsSampleDataRepository): PromptsSampleRepository {

    override fun getPromptSamplesList(): Flow<LoadResult<List<PromptSample>>> {
        return promptsSampleDataRepository.getPromptSamplesList().map { loadResult ->
            when(loadResult) {
                LoadResult.Loading -> LoadResult.Loading
                is LoadResult.Success -> LoadResult.Success(loadResult.data.map { it.toPromptSample() })
                is LoadResult.Error -> LoadResult.Error(loadResult.exception)
            }
        }
    }

}