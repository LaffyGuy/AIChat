package com.project.glue.prompts.mappers

import com.project.data.promptsample.entities.PromptSampleDataEntity
import com.project.features.prompts.domain.entities.PromptSample

fun PromptSampleDataEntity.toPromptSample(): PromptSample {
    return PromptSample(
        id = id,
        title = title,
        promptSample = promptSample,
        promptStructure = promptStructure,
        promptsExample = promptsExample
    )
}
