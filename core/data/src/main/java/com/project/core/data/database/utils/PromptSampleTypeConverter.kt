package com.project.core.data.database.utils

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

class PromptSampleTypeConverter {

    @TypeConverter
    fun fromList(list: List<String>): String = Json.encodeToString(list)

    @TypeConverter
    fun toList(data: String): List<String> {
        return if(data.isEmpty()) {
            emptyList()
        } else {
            Json.decodeFromString(data)
        }
    }
}