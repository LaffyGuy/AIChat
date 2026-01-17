package com.project.features.chats.domain.repositories

import com.project.essentials.LoadResult
import com.project.features.chats.domain.entities.Chat
import kotlinx.coroutines.flow.Flow

interface ChatsRepository {

    fun getChats(): LoadResult<Flow<List<Chat>>>

    suspend fun deleteChat(chatId: Long)

    suspend fun getChatById(chatId: Long): Chat

}