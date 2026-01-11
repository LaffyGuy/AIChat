package com.project.data.promptsample

import com.project.core.data.database.dao.PromptSampleDao
import com.project.core.data.database.model.PromptSampleEntity
import com.project.data.PromptsSampleDataRepository
import com.project.data.promptsample.entities.PromptSampleDataEntity
import com.project.data.promptsample.entities.toPromptSampleDataEntity
import com.project.essentials.LoadResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class PromptsSampleDataRepositoryImpl @Inject constructor(private val promptSampleDao: PromptSampleDao): PromptsSampleDataRepository {

    override fun getPromptSamplesList(): Flow<LoadResult<List<PromptSampleDataEntity>>> {
        return promptSampleDao.getAllPromptSamples()
            .map<List<PromptSampleEntity>, LoadResult<List<PromptSampleDataEntity>>>  { list ->
                LoadResult.Success(list.map { it.toPromptSampleDataEntity() })
        }
            .onStart { emit(LoadResult.Loading) }
            .catch { emit(LoadResult.Error(it as Exception)) }
    }
}