package com.project.core.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.core.data.database.model.ChatMessageEntity
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

    @Query("SELECT * FROM chat_message_entity WHERE chatId = :chatId ORDER BY timestamp ASC")
    fun getMessagesByChatId(chatId: Long): Flow<List<ChatMessageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: ChatMessageEntity)

    @Query("UPDATE chat_session SET isFavorite =:isFavorite WHERE id =:chatId")
    suspend fun updateFavoriteStatus(chatId: Long, isFavorite: Boolean)

    @Query("SELECT * FROM chat_session WHERE isFavorite = 1 ORDER BY createdAt DESC")
    fun getAllFavoritesChat(): Flow<List<ChatSessionEntity>>

    @Query("SELECT * FROM chat_session WHERE title LIKE '%' || :searchQuery || '%'")
    fun searchChats(searchQuery: String): Flow<List<ChatSessionEntity>>

    @Query("SELECT * FROM chat_session WHERE isFavorite = 1 AND title LIKE '%' || :searchQuery || '%'")
    fun searchFavoriteChats(searchQuery: String): Flow<List<ChatSessionEntity>>

}