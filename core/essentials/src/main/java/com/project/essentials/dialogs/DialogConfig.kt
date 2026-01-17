package com.project.essentials.dialogs

data class DialogConfig(
    val title: String,
    val message: String,
    val positiveButton: String,
    val negativeButton: String? = null
)
