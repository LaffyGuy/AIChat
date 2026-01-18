package com.project.features.main.domain

import com.project.features.main.domain.entities.MainChatSession

interface SaveNewChatUseCase {

    suspend operator fun invoke(chatSession: MainChatSession): Long

}