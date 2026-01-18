package com.project.features.chats.domain.usecases

import com.project.essentials.LoadResult
import com.project.features.chats.domain.GetChatsUseCase
import com.project.features.chats.domain.entities.ChatSession
import com.project.features.chats.domain.repositories.ChatsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChatsUseCaseImpl @Inject constructor(
    private val chatsRepository: ChatsRepository
) : GetChatsUseCase {

    override fun invoke(): Flow<LoadResult<List<ChatSession>>> {
        return chatsRepository.getChats()
    }

}