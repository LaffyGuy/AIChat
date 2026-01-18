package com.project.data

import com.project.data.aichat.entities.ChatSessionDataEntity
import com.project.essentials.LoadResult
import kotlinx.coroutines.flow.Flow

interface ChatsDataRepository {

    fun getAllChats(): Flow<LoadResult<List<ChatSessionDataEntity>>>

    suspend fun getChatById(chatId: Long): ChatSessionDataEntity

    suspend fun deleteChat(chatId: Long)

}