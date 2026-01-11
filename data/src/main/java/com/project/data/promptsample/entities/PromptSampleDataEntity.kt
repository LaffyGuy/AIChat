package com.project.data.promptsample.entities

import com.project.core.data.database.model.PromptSampleEntity

data class PromptSampleDataEntity(
    val id: Long,
    val title: String,
    val promptSample: List<String>,
    val promptStructure: List<String>,
    val promptsExample: List<String>
)

fun PromptSampleEntity.toPromptSampleDataEntity(): PromptSampleDataEntity {
    return PromptSampleDataEntity(
        id = id,
        title = title,
        promptSample = promptSample,
        promptStructure = promptStructure,
        promptsExample = promptExample
    )
}
