package com.project.features.init.domain.entities

import com.project.essentials.entities.ImageSource
import java.time.ZonedDateTime

data class KeyFeature(
    val id: Long,
    val title: String,
    val image: ImageSource,
    val description: String,
    internal val lastDisplayTime: ZonedDateTime
)
