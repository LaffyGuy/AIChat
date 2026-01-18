package com.project.core.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.project.core.data.database.model.ChatMessageEntity
import com.project.core.data.database.model.ChatSessionEntity
import com.project.essentials.LoadResult
import kotlinx.coroutines.flow.Flow

@Dao
interface AIChatDao {

    @Query("SELECT * FROM chat_session")
    fun getAllChats(): Flow<List<ChatSessionEntity>>

    @Query("SELECT * FROM chat_message_entity WHERE id = :chatId")
    suspend fun getChatById(chatId: Long): ChatMessageEntity

    @Insert
    suspend fun addNewChat(chatSessionEntity: ChatSessionEntity): Long

}