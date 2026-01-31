package com.project.glue.prompts.mappers

import com.project.data.promptsample.entities.PromptSampleDataEntity
import com.project.features.prompts.domain.entities.PromptSample
import com.project.features.prompts.presentation.promptssample.utils.getIconForPrompt

fun PromptSampleDataEntity.toPromptSample(): PromptSample {
    return PromptSample(
        id = id,
        title = title,
        imageRes = getIconForPrompt(id),
        promptSample = promptSample,
        promptStructure = promptStructure,
        promptsExample = promptsExample
    )
}
