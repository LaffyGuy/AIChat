package com.project.features.prompts.presentation.promptssample.utils

import com.project.features.prompts.presentation.R

fun getIconForPrompt(id: Long): Int {
    return when(id) {
        1L -> R.drawable.programming_image
        2L -> R.drawable.marketing_image
        3L -> R.drawable.design_image
        4L -> R.drawable.education_image
        5L -> R.drawable.writing_image
        else -> R.drawable.ic_placeholder
    }
}