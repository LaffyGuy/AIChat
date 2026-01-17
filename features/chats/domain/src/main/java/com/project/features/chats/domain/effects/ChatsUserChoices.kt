package com.project.features.chats.domain.effects

import com.project.features.chats.domain.entities.Chat

interface ChatsUserChoices {

    suspend fun confirmChatDeletion(chat: Chat): Boolean

}