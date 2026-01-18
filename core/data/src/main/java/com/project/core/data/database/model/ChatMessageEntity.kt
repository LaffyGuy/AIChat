package com.project.core.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.project.essentials.entities.MessageAuthor

@Entity(tableName = "chat_message_entity")
data class ChatMessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val text: String,
    val author: MessageAuthor,
    val timestamp: Long = System.currentTimeMillis()
)
