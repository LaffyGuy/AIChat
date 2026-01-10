package com.project.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.project.core.data.database.dao.PromptSampleDao
import com.project.core.data.database.model.PromptSampleEntity

@Database(entities = [PromptSampleEntity::class], version = 1)
abstract class ChatDataBase: RoomDatabase() {

    abstract fun getPromptSampleDao(): PromptSampleDao
}