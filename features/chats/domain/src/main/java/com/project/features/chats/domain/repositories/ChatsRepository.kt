package com.project.features.chats.domain.repositories

import com.project.essentials.LoadResult
import com.project.features.chats.domain.entities.ChatSession
import kotlinx.coroutines.flow.Flow

interface ChatsRepository {

    fun getChats(): Flow<LoadResult<List<ChatSession>>>

    suspend fun deleteChat(chatId: Long)

    suspend fun getChatById(chatId: Long): ChatSession

    suspend fun updateFavoriteChatStatus(chatId: Long, isFavorite: Boolean)

}