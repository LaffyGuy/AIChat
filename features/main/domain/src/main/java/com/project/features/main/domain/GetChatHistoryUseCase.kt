package com.project.features.main.domain

import com.project.features.main.domain.entities.ChatMessage
import kotlinx.coroutines.flow.Flow

interface GetChatHistoryUseCase {

    operator fun invoke(chatId: Long): Flow<List<ChatMessage>>

}