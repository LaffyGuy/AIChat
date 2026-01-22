package com.project.core.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.core.data.database.model.PromptSampleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PromptSampleDao {

    @Query("SELECT COUNT(*) FROM prompt_sample")
    suspend fun count(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(list: List<PromptSampleEntity>)

    @Query("SELECT * FROM prompt_sample")
    fun getAllPromptSamples(): Flow<List<PromptSampleEntity>>

    @Query("SELECT * FROM prompt_sample WHERE id = :id")
    suspend fun getPromptSampleById(id: Long): PromptSampleEntity

}