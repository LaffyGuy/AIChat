package com.project.data.chats

import com.project.core.data.database.dao.AIChatDao
import com.project.core.data.database.model.ChatSessionEntity
import com.project.data.ChatsDataRepository
import com.project.data.aichat.entities.ChatSessionDataEntity
import com.project.data.chats.mappers.toChatSessionDataEntity
import com.project.essentials.LoadResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class ChatsDataRepositoryImpl @Inject constructor(private val aiChatDao: AIChatDao): ChatsDataRepository {

    override fun getAllChats(): Flow<LoadResult<List<ChatSessionDataEntity>>> {
        return aiChatDao.getAllChats()
            .map<List<ChatSessionEntity>, LoadResult<List<ChatSessionDataEntity>>> { list ->
                LoadResult.Success(list.map { it.toChatSessionDataEntity() })
            }
            .onStart { emit(LoadResult.Loading) }
            .catch {
                emit(LoadResult.Error(it as Exception))
            }
    }

    override suspend fun getChatById(chatId: Long): ChatSessionDataEntity {
        return aiChatDao.getChatById(chatId).toChatSessionDataEntity()
    }

    override suspend fun deleteChat(chatId: Long) {
        aiChatDao.deleteChat(chatId)
    }

}