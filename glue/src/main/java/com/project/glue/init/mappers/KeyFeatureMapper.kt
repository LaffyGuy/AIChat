package com.project.glue.init.mappers

import com.project.data.keyfeatures.entities.KeyFeatureDataEntity
import com.project.features.init.domain.entities.KeyFeature
import java.time.ZonedDateTime

fun KeyFeatureDataEntity.toKeyFeature(lastDisplayTime: ZonedDateTime): KeyFeature {
    return KeyFeature(
        id = id,
        title = title,
        description = description,
        image = image,
        lastDisplayTime = lastDisplayTime
    )
}