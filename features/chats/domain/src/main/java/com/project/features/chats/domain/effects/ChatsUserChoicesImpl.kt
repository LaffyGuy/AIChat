package com.project.features.chats.domain.effects

import com.project.essentials.dialogs.DialogConfig
import com.project.essentials.dialogs.Dialogs
import com.project.essentials.resources.CoreStringProvider
import com.project.features.chats.domain.entities.Chat
import com.project.features.chats.domain.resources.ChatsStringProvider
import javax.inject.Inject

class ChatsUserChoicesImpl @Inject constructor(
    private val dialogs: Dialogs,
    private val chatsStringProvider: ChatsStringProvider,
    private val coreStringProvider: CoreStringProvider
): ChatsUserChoices {

    override suspend fun confirmChatDeletion(chat: Chat): Boolean {
       val config = DialogConfig(
           title = chatsStringProvider.confirmDeleteDialogTitle,
           message = chatsStringProvider.confirmDeleteDialogMessage(chat.title),
           positiveButton = coreStringProvider.deleteAction,
           negativeButton = coreStringProvider.cancelAction
       )
        return dialogs.showAlertDialog(config)
    }
}