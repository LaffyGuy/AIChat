package com.project.features.chats.domain

import com.project.essentials.LoadResult
import com.project.features.chats.domain.entities.ChatSession
import kotlinx.coroutines.flow.Flow

interface GetSearchChatsUseCase {

    operator fun invoke(searchQuery: String): Flow<LoadResult<List<ChatSession>>>

}