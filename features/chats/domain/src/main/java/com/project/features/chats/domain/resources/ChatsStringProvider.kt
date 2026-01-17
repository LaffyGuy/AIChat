package com.project.features.chats.domain.resources

import com.project.essentials.resources.StringProvider

interface ChatsStringProvider: StringProvider {

    val confirmDeleteDialogTitle: String
    fun confirmDeleteDialogMessage(title: String): String

}