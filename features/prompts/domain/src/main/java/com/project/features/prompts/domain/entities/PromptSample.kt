package com.project.features.prompts.domain.entities

data class PromptSample(
    val id: Long,
    val title: String,
    val promptSample: List<String>,
    val promptStructure: List<String>,
    val promptsExample: List<String>
)
