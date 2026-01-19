package com.project.core.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.core.data.database.model.ChatSessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AIChatDao {

    @Query("SELECT * FROM chat_session")
    fun getAllChats(): Flow<List<ChatSessionEntity>>

    @Query("SELECT * FROM chat_session WHERE id = :chatId")
    suspend fun getChatById(chatId: Long): ChatSessionEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNewChat(chatSessionEntity: ChatSessionEntity): Long

    @Query("DELETE FROM chat_session WHERE id = :chatId")
    suspend fun deleteChat(chatId: Long)

}