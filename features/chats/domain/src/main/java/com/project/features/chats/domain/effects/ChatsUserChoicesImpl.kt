package com.project.features.chats.domain.effects

import com.project.essentials.dialogs.DialogConfig
import com.project.essentials.dialogs.Dialogs
import com.project.features.chats.domain.entities.Chat
import javax.inject.Inject

class ChatsUserChoicesImpl @Inject constructor(
    private val dialogs: Dialogs
): ChatsUserChoices {

    override suspend fun confirmChatDeletion(chat: Chat): Boolean {
       val config = DialogConfig(
           title = "Chat Deletion",
           message = "Are you sure you want delete the chat '${chat.title}'",
           positiveButton = "Delete",
           negativeButton = "Cancel"
       )
        return dialogs.showAlertDialog(config)
    }
}