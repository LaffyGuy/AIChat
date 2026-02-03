package com.project.features.chats.domain.mocks

import com.project.essentials.LoadResult
import com.project.features.chats.domain.entities.ChatSession
import com.project.features.chats.domain.repositories.ChatsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class MockChatRepository: ChatsRepository {

    val chatsFlow = MutableSharedFlow<LoadResult<List<ChatSession>>>()

    override fun getChats(): Flow<LoadResult<List<ChatSession>>> = chatsFlow

    override suspend fun deleteChat(chatId: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun getChatById(chatId: Long): ChatSession {
        TODO("Not yet implemented")
    }

    override suspend fun updateFavoriteChatStatus(chatId: Long, isFavorite: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getSearchChats(searchQuery: String): Flow<LoadResult<List<ChatSession>>> {
        TODO("Not yet implemented")
    }
}