package com.project.features.main.domain

import com.project.essentials.entities.MessageAuthor

interface SaveMessageUseCase {

    suspend operator fun invoke(chatId: Long, text: String, author: MessageAuthor)

}