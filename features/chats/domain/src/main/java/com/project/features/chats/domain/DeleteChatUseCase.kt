package com.project.features.chats.domain

interface DeleteChatUseCase {

    suspend operator fun invoke(chatId: Long)

}