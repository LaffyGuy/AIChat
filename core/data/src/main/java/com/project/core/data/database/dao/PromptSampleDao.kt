package com.project.core.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.project.core.data.database.model.PromptSampleEntity
import com.project.essentials.LoadResult
import kotlinx.coroutines.flow.Flow

@Dao
interface PromptSampleDao {

    @Query("SELECT * FROM prompt_sample")
    fun getAllPromptSamples(): Flow<List<PromptSampleEntity>>

    @Query("SELECT * FROM prompt_sample WHERE id = :id")
    suspend fun getPromptSampleById(id: Long): PromptSampleEntity

}