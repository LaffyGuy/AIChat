package com.project.features.chats.presentation.di

import com.project.features.chats.domain.resources.ChatsStringProvider
import com.project.features.chats.presentation.resources.ChatsStringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ChatsStringProviderModule {

    @Binds
    fun bindChatsStringProvider(
        impl: ChatsStringProviderImpl
    ): ChatsStringProvider

}