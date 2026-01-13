package com.project.data.promptsample

import com.project.core.data.database.dao.PromptSampleDao
import com.project.data.PromptSampleDetailsDataRepository
import com.project.data.promptsample.entities.PromptSampleDataEntity
import com.project.data.promptsample.entities.toPromptSampleDataEntity
import com.project.essentials.logger.Logger
import javax.inject.Inject

class PromptSampleDetailsDataRepositoryImpl @Inject constructor(private val promptSampleDao: PromptSampleDao): PromptSampleDetailsDataRepository {

    override suspend fun getPromptSampleById(promptSampleId: Long): PromptSampleDataEntity {
        val prompt = promptSampleDao.getPromptSampleById(promptSampleId).toPromptSampleDataEntity()
        Logger.d("AAAAA - promt DataEntity - $prompt")
        return prompt
//        promptSampleDao.getPromptSampleById(promptSampleId).toPromptSampleDataEntity()
    }

}