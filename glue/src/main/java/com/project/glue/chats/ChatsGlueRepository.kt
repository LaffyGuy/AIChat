package com.project.glue.chats

import com.project.data.ChatsDataRepository
import com.project.essentials.LoadResult
import com.project.features.chats.domain.entities.ChatSession
import com.project.features.chats.domain.repositories.ChatsRepository
import com.project.glue.chats.mappers.toChatSession
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ChatsGlueRepository @Inject constructor(private val aiChatDataRepository: ChatsDataRepository): ChatsRepository {

    override fun getChats(): Flow<LoadResult<List<ChatSession>>> {
        return aiChatDataRepository.getAllChats().map { loadResult ->
            when(loadResult) {
                LoadResult.Loading -> LoadResult.Loading
                is LoadResult.Success -> LoadResult.Success(loadResult.data.map { it.toChatSession() })
                is LoadResult.Error -> LoadResult.Error(loadResult.exception)
            }
        }
    }

    override suspend fun deleteChat(chatId: Long) {
        aiChatDataRepository.deleteChat(chatId)
    }

    override suspend fun getChatById(chatId: Long): ChatSession {
       return aiChatDataRepository.getChatById(chatId).toChatSession()
    }

    override suspend fun updateFavoriteChatStatus(chatId: Long, isFavorite: Boolean) {
        aiChatDataRepository.updateFavoriteStatus(chatId, isFavorite)
    }

    override fun getSearchChats(searchQuery: String): Flow<LoadResult<List<ChatSession>>> {
        return aiChatDataRepository.searchChats(searchQuery).map { loadResult ->
            when(loadResult) {
                LoadResult.Loading -> LoadResult.Loading
                is LoadResult.Success -> LoadResult.Success(loadResult.data.map { it.toChatSession() })
                is LoadResult.Error -> LoadResult.Error(loadResult.exception)
            }
        }
    }

}