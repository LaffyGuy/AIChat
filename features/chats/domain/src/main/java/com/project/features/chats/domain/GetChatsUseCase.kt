package com.project.features.chats.domain

import com.project.essentials.LoadResult
import com.project.features.chats.domain.entities.ChatSession
import kotlinx.coroutines.flow.Flow

interface GetChatsUseCase {

    operator fun invoke(): Flow<LoadResult<List<ChatSession>>>

}