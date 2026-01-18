package com.project.features.main.domain

import com.project.features.main.domain.entities.ChatSession

interface SaveNewChatUseCase {

    suspend operator fun invoke(chatSession: ChatSession): Long

}