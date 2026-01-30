package com.project.features.main.domain

import com.project.essentials.entities.MessageAuthor
import com.project.features.main.domain.entities.ChatMessage

interface SaveMessageUseCase {

    suspend operator fun invoke(chatMessage: ChatMessage)

}