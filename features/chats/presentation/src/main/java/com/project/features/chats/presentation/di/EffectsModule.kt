package com.project.features.chats.presentation.di

import com.project.features.chats.domain.effects.ChatsUserChoices
import com.project.features.chats.domain.effects.ChatsUserChoicesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface EffectsModule {

    @Binds
    fun bindChatsUserChoices(
        impl: ChatsUserChoicesImpl
    ): ChatsUserChoices

}