package com.project.features.chats.presentation.resources

import android.content.Context
import com.project.features.chats.domain.resources.ChatsStringProvider
import com.project.features.chats.presentation.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ChatsStringProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
): ChatsStringProvider {

    override val confirmDeleteDialogTitle = context.getString(R.string.chat_deletion_title)

    override fun confirmDeleteDialogMessage(title: String) = context.getString(R.string.chat_deletion_message, title)
}