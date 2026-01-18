package com.project.core.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat_session")
data class ChatSessionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String
)