package com.project.features.chats.domain.usecases

import com.project.essentials.LoadResult
import com.project.features.chats.domain.GetSearchChatsUseCase
import com.project.features.chats.domain.entities.ChatSession
import com.project.features.chats.domain.repositories.ChatsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchChatsUseCaseImpl @Inject constructor(private val chatsRepository: ChatsRepository): GetSearchChatsUseCase {

    override fun invoke(searchQuery: String): Flow<LoadResult<List<ChatSession>>> {
        return chatsRepository.getSearchChats(searchQuery)
    }

}