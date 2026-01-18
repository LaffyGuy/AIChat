package com.project.features.chats.domain.effects

import com.project.features.chats.domain.entities.ChatSession

interface ChatsUserChoices {

    suspend fun confirmChatDeletion(chat: ChatSession): Boolean

}