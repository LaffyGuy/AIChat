package com.project.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.project.core.data.database.dao.AIChatDao
import com.project.core.data.database.dao.PromptSampleDao
import com.project.core.data.database.model.ChatMessageEntity
import com.project.core.data.database.model.ChatSessionEntity
import com.project.core.data.database.model.PromptSampleEntity
import com.project.core.data.database.utils.PromptSampleTypeConverter

@TypeConverters(PromptSampleTypeConverter::class)
@Database(
    entities = [
        PromptSampleEntity::class,
        ChatSessionEntity::class,
        ChatMessageEntity::class
               ],
    version = 6)
abstract class ChatDataBase: RoomDatabase() {

    abstract fun getPromptSampleDao(): PromptSampleDao

    abstract fun getAIChatDao(): AIChatDao
}